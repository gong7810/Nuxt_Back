package kr.co.seoulit.erp.account.slip.applicationservice;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import kr.co.seoulit.erp.account.funds.dao.NoteDAO;
import kr.co.seoulit.erp.account.funds.to.NoteBean;
import kr.co.seoulit.erp.account.slip.repository.JournalDetailRepository;
import kr.co.seoulit.erp.account.funds.repository.NoteRepository;
import kr.co.seoulit.erp.account.slip.repository.SlipReposiotry;
import kr.co.seoulit.erp.account.slip.repository.ReceiptReposiotry;
import kr.co.seoulit.erp.account.slip.to.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import kr.co.seoulit.erp.account.slip.dao.SlipDAO;
import kr.co.seoulit.erp.account.slip.dao.JournalDAO;
import kr.co.seoulit.erp.account.slip.dao.JournalDetailDAO;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class SlipApplicationServiceImpl implements SlipApplicationService {

	private final SlipDAO slipDAO;
	private final JournalDAO journalDAO;
	private final JournalDetailDAO journalDetailDAO;
	private final NoteDAO noteDAO;
	private final SlipReposiotry slipReposiotry;
	private final JournalDetailRepository journalDetailRepository;
	private final ReceiptReposiotry receiptReposiotry;
	private final NoteRepository noterepository;

	//전표 전제조회
	@Override
	public ArrayList<SlipBean> findSlipList() {
		ArrayList<SlipBean> all = slipDAO.fetchSlip();
		return all;
	}

	//승인 / 반려 전표 조회
	@Override
	public ArrayList<SlipBean> findStatusSlipList(String status) {
		ArrayList<SlipBean> statusSlipList = slipDAO.fetchStatusSlipList(status);

		return statusSlipList;
	}

	//미결 전표 조회
	@Override
	public ArrayList<SlipBean> findUnProSlipList() {
		ArrayList<SlipBean> unProSlipList = slipDAO.fetchUnProSlipList();

		return unProSlipList;
	}

	//전표 한개조회
	@Override
	public SlipBean findSlipBySlipNo(String slipNo) {
		Optional<SlipBean> slipbean = slipDAO.fetchSlipBySlipNo(slipNo);
		if(!slipbean.isPresent()){
			throw new RuntimeException("존재하지않는 전표입니다");
		}
		ArrayList<JournalBean> journalList = journalDAO.selectJournalList(slipNo);
		journalList.forEach(journalBean->
				journalBean.setJournalDetailList(journalDetailRepository.findByJournalNo(journalBean.getJournalNo())));

		// 계정과목이 어음이면 어음데이터까지
		ArrayList<NoteBean> noteList = new ArrayList<>();
		for (JournalBean journalBean : journalList) {
			NoteBean noteBean = noteDAO.findNoteByJournalNo(journalBean.getJournalNo());
			if (noteBean != null)
				noteList.add(noteBean);
		}
		SlipBean slipBean = slipbean.get();
		slipBean.setNoteDetail(noteList);
		slipBean.setJournalList(journalList);
		return slipBean;
	}

	//전표 등록
	@Override
	public String registerSlip(SlipBean slipBean) {

		StringBuffer slipNo = new StringBuffer();
		String slipNoDate = slipBean.getReportingDate().replace("-", "");
		slipNo.append(slipNoDate);
		slipNo.append("SLIP"); // 20231116SLIP

		ArrayList<JournalBean> journalList = slipBean.getJournalList();

		// 이렇게 하면 앞 전표를 삭제했을시 계속 덮어쓰게 됨
//		String code = "0000" + (slipDAO.selectSlipCount(slipNoDate) + 1) + "";

		String lastNo = slipDAO.selectSlipMaxNo(slipNoDate);
		if (lastNo == null){
			lastNo = "00000";
		}
		int length = lastNo.length();

		String code = "0000" + (Integer.parseInt(lastNo.substring(length - 5)) + 1) + "";
		slipNo.append(code.substring(code.length() - 5)); // 20231116SLIP00001
		slipBean.setSlipNo(slipNo.toString());

		String year = slipBean.getReportingDate().substring(0,4);
		String periodNo = (Integer.parseInt(year)-2019)+"";
		slipBean.setAccountPeriodNo(periodNo);

		slipReposiotry.save(slipBean);

		ArrayList<NoteBean> noteList = slipBean.getNoteDetail();

		for (JournalBean journalBean : journalList) {
			String SlipNo = slipBean.getSlipNo();
			String sampleJournalNo = journalBean.getJournalNo();
			String journalNo = journalDAO.selectJournalName(SlipNo);
			journalBean.setSlipNo(SlipNo);
			journalBean.setJournalNo(journalNo);
			journalBean.setAccountInnerCode(journalBean.getAccountCode());
			journalDAO.insertJournal(journalBean);

			// 계정과목에 어음이 존재시
			if (noteList.size() != 0) {
				for(NoteBean noteBean : noteList) {
					if (noteBean.getJournalNo().equals(sampleJournalNo)) {
						System.out.println("저장합니다");
						noteBean.setJournalNo(journalNo);
						noteBean.setNoteType(noteBean.getNoteType().substring(noteBean.getNoteType().length()-2, noteBean.getNoteType().length())); // 뒤에서 2자리
						noterepository.save(noteBean);
						break;
					}
				}
			}

			for(JournalDetailBean journalDetailBean : journalBean.getJournalDetailList()) {
				String newJournalDetailNo = journalDetailDAO.getMaxJournalDetailNo();
				journalDetailBean.setJournalDetailNo(newJournalDetailNo);
				journalDetailBean.setJournalNo(journalBean.getJournalNo());
				journalDetailRepository.save(journalDetailBean);
			}
		}

		// 지출증빙 테이블과 연동
		ReceiptBean receiptBean = new ReceiptBean();

		receiptBean.setSlipNo(slipBean.getSlipNo());
		receiptBean.setAccountPeriodNo(slipBean.getAccountPeriodNo());
		receiptBean.setReportingDate(slipBean.getReportingDate());
		receiptBean.setExpenseReport(slipBean.getExpenseReport());

		receiptReposiotry.save(receiptBean);

		return slipNo.toString();
	}

	//전표 리스트등록
	@Override
	public String registerSlipList(ArrayList<SlipBean> slipList) {

		try {
			for (SlipBean slipBean : slipList) {

				StringBuffer slipNo = new StringBuffer();
				String slipNoDate = slipBean.getReportingDate().replace("-", "");
				slipNo.append(slipNoDate);
				slipNo.append("SLIP"); // 20190717SLIP

				ArrayList<JournalBean> journalList = slipBean.getJournalList();

				// 이렇게 하면 앞 전표를 삭제했을시 계속 덮어쓰게 됨
		//		String code = "0000" + (slipDAO.selectSlipCount(slipNoDate) + 1) + "";

				String lastNo = slipDAO.selectSlipMaxNo(slipNoDate);
				if (lastNo == null){
					lastNo = "00000";
				}
				int length = lastNo.length();

				String code = "0000" + (Integer.parseInt(lastNo.substring(length - 5)) + 1) + "";
				slipNo.append(code.substring(code.length() - 5));
				slipBean.setSlipNo(slipNo.toString());

				String year = slipBean.getReportingDate().substring(0,4);
				String periodNo = (Integer.parseInt(year)-2019)+"";
				slipBean.setAccountPeriodNo(periodNo);

				slipReposiotry.save(slipBean);

				ArrayList<NoteBean> noteList = slipBean.getNoteDetail();

				for (JournalBean journalBean : journalList) {
					String SlipNo = slipBean.getSlipNo();
					String sampleJournalNo = journalBean.getJournalNo();
					String journalNo = journalDAO.selectJournalName(SlipNo);
					journalBean.setSlipNo(SlipNo);
					journalBean.setJournalNo(journalNo);
					journalBean.setAccountInnerCode(journalBean.getAccountCode());
					journalDAO.insertJournal(journalBean);

					// 계정과목에 어음이 존재시
					if (noteList.size() != 0) {
						for(NoteBean noteBean : noteList) {
							if (noteBean.getJournalNo().equals(sampleJournalNo)) {
								System.out.println("어음 저장합니다");
								noteBean.setJournalNo(journalNo);
								noteBean.setNoteType(noteBean.getNoteType().substring(noteBean.getNoteType().length()-2, noteBean.getNoteType().length())); // 뒤에서 2자리
								noterepository.save(noteBean);
								break;
							}
						}
					}

					for(JournalDetailBean journalDetailBean : journalBean.getJournalDetailList()) {
						String newJournalDetailNo = journalDetailDAO.getMaxJournalDetailNo();
						journalDetailBean.setJournalDetailNo(newJournalDetailNo);
						journalDetailBean.setJournalNo(journalBean.getJournalNo());
						journalDetailRepository.save(journalDetailBean);
					}
				}

				// 지출증빙 필요전표로 연쇄등록
				ReceiptBean receiptBean = new ReceiptBean();

				receiptBean.setSlipNo(slipBean.getSlipNo());
				receiptBean.setAccountPeriodNo(slipBean.getAccountPeriodNo());
				receiptBean.setReportingDate(slipBean.getReportingDate());
				receiptBean.setExpenseReport(slipBean.getExpenseReport());

				receiptReposiotry.save(receiptBean);
			}
			return "추가 성공";
		} catch (Exception e) {
			return "추가 실패하였습니다";
		}
	}

	//전표 리스트삭제
	@Override
	public void deleteSlip(ArrayList<SlipBean> slipList) {
		for (SlipBean slipBean : slipList) {
			for (JournalBean journalBean: slipBean.getJournalList()) {
				for (JournalDetailBean journalDetailBean : journalBean.getJournalDetailList()) {
					System.out.println("분개상세 삭제시작");
					journalDetailDAO.deleteJournalDetailByJournalNo(journalDetailBean.getJournalNo());
				}
				System.out.println("분개상세 삭제완료");
				System.out.println("분개 삭제시작");
				journalDAO.deleteJournal(journalBean.getSlipNo());
				// 어음일시 어음도 같이 삭제
				noteDAO.delelteNote(journalBean.getJournalNo());
			}
			System.out.println("분개 삭제완료");
			System.out.println("전표 삭제시작");
			slipReposiotry.deleteById(slipBean.getSlipNo());

			System.out.println("지출증빙 삭제시작");
			// 지출증빙 전표에서도 연쇄삭제
			deleteReceiptFile(slipBean.getSlipNo());
			receiptReposiotry.deleteById(slipBean.getSlipNo());
		}
		System.out.println("지출증빙 삭제완료");
		System.out.println("전표 삭제완료");
	}

	//전표 리스트승인
	@Override
	public void approveSlip(ArrayList<SlipBean> slipList) {
		for (SlipBean slipBean : slipList){
			Optional<SlipBean> slip = slipReposiotry.findById(slipBean.getSlipNo());
			if(!slip.isPresent()){
				throw  new RuntimeException(String.format("해당 [%s] 전표는 존재하지않습니다",slipBean.getSlipNo()));
			}
			slipReposiotry.save(slipBean);
		}
	}

	//전표 수정
	@Override
	public void updateSlip(SlipBean slipBean) {

		slipReposiotry.save(slipBean);

		ArrayList<NoteBean> noteList = slipBean.getNoteDetail();

		// 계정과목에 어음이 존재시
		if (noteList.size() != 0) {
			for(NoteBean noteBean : noteList) {
				noteBean.setNoteType(noteBean.getNoteType().substring(noteBean.getNoteType().length()-2, noteBean.getNoteType().length())); // 뒤에서 2자리
				noteDAO.updateNote(noteBean);
			}
		}

		for(JournalBean journalBean: slipBean.getJournalList()){
			journalDAO.updateJournal(journalBean);

			for(JournalDetailBean journalDetailBean: journalBean.getJournalDetailList()){
				journalDetailDAO.updateJournalDetail((journalDetailBean));
			}

		}

		// 전표변경에 따른 연쇄변경
		System.out.println("증빙테이블에도 연동");
		ReceiptBean receiptBean = receiptReposiotry.findBySlipNo(slipBean.getSlipNo());
		
		receiptBean.setAccountPeriodNo(slipBean.getAccountPeriodNo());
		receiptBean.setReportingDate(slipBean.getReportingDate());
		receiptBean.setExpenseReport(slipBean.getExpenseReport());
		receiptBean.setProofStatus(slipBean.getAuthorizationStatus());

		receiptReposiotry.save(receiptBean);

		deleteReceiptFile(slipBean.getSlipNo());
	}

	//분개 전체조회
	@Override
	public ArrayList<JournalBean> findJournalListBySlipNo(String slipNo) {
		ArrayList<JournalBean> journalList = journalDAO.selectJournalList(slipNo);
		journalList.forEach(journalBean->
				journalBean.setJournalDetailList(journalDetailRepository.findByJournalNo(journalBean.getJournalNo())));

		return  journalList;
	}

	//분개장 조회
	@Override
	public ArrayList<JournalDoubleBean> findJournalDoubleList(String fromDate, String toDate) {
		HashMap<String, String> map = new HashMap<>();
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		return journalDAO.selectJournalDoubleList(map);
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////

	//지출증빙 전체조회
	@Override
	public ArrayList<ReceiptBean> findReceiptList() {
		ArrayList<ReceiptBean> all = receiptReposiotry.findAllByOrderBySlipNo();
		return all;
	}

	//지출증빙 한개조회
	@Override
	public ReceiptBean findReceiptBySlipNo(String slipNo) {
		Optional<ReceiptBean> receiptbean = receiptReposiotry.findById(slipNo);
		if(!receiptbean.isPresent()){
			throw new RuntimeException("존재하지않는 전표입니다");
		}
		ReceiptBean receiptBean = receiptbean.get();
		return receiptBean;
	}

	//지출증빙 증빙등록
	@Override
	public void registerReceipt(String slipNo, String type, MultipartFile file) {

		ReceiptBean receiptBean = receiptReposiotry.findBySlipNo(slipNo);
		receiptBean.setProofStatus(""); //영수증 첨부시 증빙상태 초기화

		try {
			// 파일이 비어있지 않은 경우에만 처리
			if (!file.isEmpty()) {

				// filePath는 직접지정해줘야함
				//노트북
//                 String filePath = "C:\\Users\\owner\\Desktop\\77th_1st_Vue ACC\\77th_front\\assets\\images\\receiptImg\\" + slipNo + ".png";
				//풀버전
//                 String filePath = "C:\\Users\\owner\\Desktop\\77th_1st_Vue ACC\\77th_front_full\\assets\\images\\receiptImg\\" + slipNo + ".png";
				//발표노트북
				String filePath = "C:\\Users\\young\\OneDrive\\바탕 화면\\학생프로젝트\\77기 1차 Vue\\회계\\77th_acc_front\\assets\\images\\receiptImg\\" + slipNo + ".png";
				//데스크탑
//                String filePath = "C:\\Users\\Seong-Uk\\OneDrive - gnu.ac.kr\\바탕 화면\\77th_ACC_Nuxt\\77th_front\\assets\\images\\receiptImg\\" + slipNo + ".png";
				// 새로운 파일 저장
				Path path = Paths.get(filePath);
				Files.write(path, file.getBytes());

				receiptBean.setReceiptType(type);

				receiptReposiotry.save(receiptBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//지출증빙 증빙삭제
	@Override
	public void deleteReceiptFile(String slipNo) {

		ReceiptBean receiptBean = receiptReposiotry.findBySlipNo(slipNo);
		receiptBean.setReceiptType(""); // 증빙파일유형 초기화

		try {
			System.out.println("사진파일 삭제~~~~~" + slipNo);

			// filePath는 직접지정해줘야함
			//노트북
	//             String filePath = "C:\\Users\\owner\\Desktop\\77th_1st_Vue ACC\\77th_front\\assets\\images\\receiptImg\\" + slipNo + ".png";
			//풀버전
	//                 String filePath = "C:\\Users\\owner\\Desktop\\77th_1st_Vue ACC\\77th_front_full\\assets\\images\\receiptImg\\" + slipNo + ".png";
			//발표노트북
			String filePath = "C:\\Users\\young\\OneDrive\\바탕 화면\\학생프로젝트\\77기 1차 Vue\\회계\\77th_acc_front\\assets\\images\\receiptImg\\" + slipNo + ".png";
			//데스크탑
	//            String filePath = "C:\\Users\\Seong-Uk\\OneDrive - gnu.ac.kr\\바탕 화면\\77th_ACC_Nuxt\\77th_front\\assets\\images\\receiptImg\\" + slipNo + ".png";
			// 파일 삭제
			File file = new File(filePath);
			file.delete();

			receiptReposiotry.save(receiptBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//지출증빙 증빙완료
	@Override
	public void proofReceipt(ArrayList<ReceiptBean> receiptList) {
		for (ReceiptBean receiptBean : receiptList){
			Optional<ReceiptBean> receipt = receiptReposiotry.findById(receiptBean.getSlipNo());
			if(!receipt.isPresent()){
				throw  new RuntimeException(String.format("해당 [%s] 전표는 존재하지않습니다",receiptBean.getSlipNo()));
			}
			receiptReposiotry.save(receiptBean);

//			//전표 테이블에도 연동
			slipDAO.updateAuthorizationSlip(receiptBean);
		}
	}


















///////////////////////////////////////////////////////////////////////////////////////////////////////


	@Override
	public ArrayList<SlipBean> findSlipDataList(String slipDate) {

		return slipDAO.selectSlipDataList(slipDate);
	}

	@Override
	public String addSlip(Map<String, SlipBean> batchArray) {
		HashMap<String, String> map = new HashMap<>();
		SlipBean slipList = batchArray.get("slipData");
       ArrayList<JournalBean> journalList = null;
		SlipBean slipBean = slipList;
		StringBuffer slipNo = new StringBuffer();
		String slipNoDate = slipBean.getReportingDate().replace("-", ""); // 20190717
		slipNo.append(slipNoDate);
		slipNo.append("SLIP"); // 20190717SLIP

		journalList = slipBean.getJournalList();

		String code = "0000" + (slipDAO.selectSlipCount(slipNoDate) + 1) + ""; // 00003

		slipNo.append(code.substring(code.length() - 5)); // 00003
		slipBean.setSlipNo(slipNo.toString()); // 20190717SLIP00003
		slipBean.setApprovalEmpCode("");
		slipBean.setApprovalDate("");

		slipDAO.insertSlip(slipBean);

		for (JournalBean journalBean : journalList) {
			String SlipNo = slipBean.getSlipNo();
			String journalNo = journalDAO.selectJournalName(SlipNo);
			journalBean.setSlipNo(SlipNo);
			journalBean.setJournalNo(journalNo);
			journalDAO.insertJournal(journalBean);
			journalDetailDAO.insertJournalDetailList(journalNo);

		}

		return slipNo.toString();
	}


//	@Override
//	public void approveSlip(ArrayList<SlipBean> slipBeans) {
//		HashMap<String, String> slipNoList = new HashMap<String, String>();
//		ArrayList<JournalSeparatorBean> jouranlSetartor = new ArrayList<JournalSeparatorBean>();
//		DeliveryInfoTO deliveryInfo = new DeliveryInfoTO();
//		MonthSalaryTO monthSalary = new MonthSalaryTO();
//		System.out.println("slipBeans:::::::::" + slipBeans.get(0));
//		for (SlipBean slipBean : slipBeans) { // 분개저장 승인 반려 확인
//			slipDAO.approveSlip(slipBean);
//			slipNoList.put(slipBean.getSlipNo(), slipBean.getSlipStatus());
//		}
//
//		for (String te : slipNoList.keySet()) { // 임금,납품 반려시 마감완료되었던것 N으로 바꿔주는 작업
//			System.out.println("testtesttesttesttesttest" + te);
//			System.out.println("(String)slipNoList.get(te)" + (String) slipNoList.get(te));
//			String divison = (String) slipNoList.get(te);
//
//			if (divison.equals("반려")) {
//				System.out.println("들가나???");
//				jouranlSetartor = journalDAO.selectJournalSeparator(te);
//				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//				for (JournalSeparatorBean jsr : jouranlSetartor) {
//					if (jsr.getDeliveryNo() != null) {
//						System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//
//						deliveryInfo.setDeliveryNo(jsr.getDeliveryNo());
//						deliveryInfo.setFinalizeStatus("N");
//						System.out.println("$$$$$$$$$$$$$$$$$$:::::" + deliveryInfo);
//						deliveryDAO.deliverUpdate(deliveryInfo);
//					} else if (jsr.getEmpCode() != null) {
//						monthSalary.setApplyYearMonth(jsr.getApplyYearMonth());
//						monthSalary.setEmpCode(jsr.getEmpCode());
//						monthSalary.setFinalizeStatus("N");
//						monthSalaryDAO.updateMonthSalary(monthSalary);
//					}
//				}
//			}
//		}
//		System.out.println("#$$$zzzzzzzzzzzzzzzzzzzzz::" + jouranlSetartor);
//		System.out.println("jouranlSetartor:::::" + jouranlSetartor);
//	}

	@Override
	public ArrayList<SlipBean> findRangedSlipList(HashMap<String, Object> map) {

		System.out.println(" 전표 서비스임플  ");

		return slipDAO.selectRangedSlipList(map);
	}



	@Override
	public String hrAddSlip(Map<String, ArrayList<SlipBean>> batchArray) {

		ArrayList<SlipBean> slipList = batchArray.get("slipData");
		ArrayList<JournalBean> journalList = null;

		SlipBean slipBean = slipList.get(0);

		StringBuffer slipNo = new StringBuffer();
		String slipNoDate = slipBean.getReportingDate().replace("-", ""); // 20190717
		slipNo.append(slipNoDate);
		slipNo.append("SLIP"); // 20190717SLIP

		journalList = slipBean.getJournalList();

		String code = "0000" + (slipDAO.selectSlipCount(slipNoDate) + 1) + ""; // 00003

		slipNo.append(code.substring(code.length() - 5)); // 00003
		slipBean.setSlipNo(slipNo.toString()); // 20190717SLIP00003
		slipBean.setApprovalEmpCode("");
		slipBean.setApprovalDate("");
		slipDAO.insertSlip(slipBean);

		for (JournalBean journalBean : journalList) {
			String SlipNo = slipBean.getSlipNo();
			String journalNo = journalDAO.selectJournalName(SlipNo);
			journalBean.setSlipNo(SlipNo);
			journalBean.setJournalNo(journalNo);
			journalDAO.insertJournal(journalBean);
			journalDetailDAO.insertJournalDetailList(journalNo);
		}

		return slipNo.toString();
	}

	//분개
	@Override
	public ArrayList<JournalBean> findRangedJournalList(String fromDate, String toDate) {
		HashMap<String, String> map = new HashMap<>();
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		return journalDAO.selectRangedJournalList(map);
	}

	@Override
	public ArrayList<JournalBean> findSingleJournalList(String slipNo) {

		return journalDAO.selectJournalList(slipNo);
	}

	// ===================================== 일반전표 분개삭제생성 XML까지 (XML은 수정 ) 2020-09-07
	//  시작 ====================================
	@Override
	public void deleteJournalRow(String journalNo) {
		journalDAO.deleteJournal(journalNo);

	}

	// ===================================== 일반전표 분개update 생성 2020-09-12  시작
	// ====================================
	@Override
	public void updateJournalList(HashMap<String, ArrayList<JournalBean>> journalList) {

		ArrayList<JournalBean> journalLists = journalList.get("journalList");

		for (JournalBean newJournalBeans : journalLists) {

			System.out.println("분개저장 UPDATE 서비스임플 : " + newJournalBeans);
			journalDAO.updateJournal(newJournalBeans);
		}
	}





	//분개상세

	@Override
	public ArrayList<JournalDetailBean> getJournalDetailList(String journalNo) {
		System.out.println("나오나1?" + journalNo);
		return journalDetailDAO.selectJournalDetailList(journalNo);
	}

	@Override
	public void addJournalDetailList(String journalNo) {

		journalDetailDAO.insertJournalDetailList(journalNo);

	}

	@Override
	public void editJournalDetail(JournalDetailBean journalDetailBean) {

		journalDetailDAO.updateJournalDetail(journalDetailBean);

	}

}
