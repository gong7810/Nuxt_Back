package kr.co.seoulit.erp.account.slip.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.co.seoulit.erp.account.slip.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.erp.account.slip.applicationservice.SlipApplicationService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SlipServiceFacadeImpl implements SlipServiceFacade {

	@Autowired
	private SlipApplicationService slipApplicationService;

	//전표 전체조회
	@Override
	public ArrayList<SlipBean> findSlipList() {
		return slipApplicationService.findSlipList();
	}

	//승인 / 반려 전표 조회
	@Override
	public ArrayList<SlipBean> findStatusSlipList(String status) {
		return slipApplicationService.findStatusSlipList(status);
	}

	//미결 전표 조회
	@Override
	public ArrayList<SlipBean> findUnProSlipList() {
		return slipApplicationService.findUnProSlipList();
	}

	//전표 한개조회
	@Override
	public SlipBean findSlipBySlipNo(String slipNo) {
		return slipApplicationService.findSlipBySlipNo(slipNo);
	}

	//전표 한개등록
	@Override
	public String registerSlip(SlipBean slipBean) {
		return slipApplicationService.registerSlip(slipBean);
	}

	//전표 리스트등록
	@Override
	public String registerSlipList(ArrayList<SlipBean> slipList) {
		return slipApplicationService.registerSlipList(slipList);
	}

	//전표 리스트삭제
	@Override
	public void deleteSlip(ArrayList<SlipBean> slipList) {
		slipApplicationService.deleteSlip(slipList);
	}

	//잔표 리스트승인
	@Override
	public void approveSlip(ArrayList<SlipBean> slipList) {
		slipApplicationService.approveSlip(slipList);
	}

	//전표수정
	@Override
	public void updateSlip(SlipBean slipBean) {
		slipApplicationService.updateSlip(slipBean);
	}

	//분개 전체조회
	@Override
	public ArrayList<JournalBean> findJournalListBySlipNo(String slipNo) {
		return slipApplicationService.findJournalListBySlipNo(slipNo);
	}

////////////////////////////////////////////////////////////////////////

	//지출증빙 전체조회
	@Override
	public ArrayList<ReceiptBean> findReceiptList() {
		return slipApplicationService.findReceiptList();
	}

	//지출증빙 한개조회
	@Override
	public ReceiptBean findReceiptBySlipNo(String slipNo) {
		return slipApplicationService.findReceiptBySlipNo(slipNo);
	}

	//지출증빙 증빙등록
	@Override
	public void registerReceipt(String slipNo, String type, MultipartFile file) {
		slipApplicationService.registerReceipt(slipNo, type, file);
	}

	//지출증빙 증빙삭제
	@Override
	public void deleteReceiptFile(String slipNo) {
		slipApplicationService.deleteReceiptFile(slipNo);
	}
	
	//지출증빙 증빙완료
	@Override
	public void proofReceipt(ArrayList<ReceiptBean> receiptList) {
		slipApplicationService.proofReceipt(receiptList);
	}



























////////////////////////////////////////////////////////////////////////


	@Override
	public String addSlip(Map<String, SlipBean> batchArray) {
		return slipApplicationService.addSlip(batchArray);
	}




	@Override
	public ArrayList<SlipBean> findSlipDataList(String slipDate) {
		return slipApplicationService.findSlipDataList(slipDate);
	}

	@Override
	public ArrayList<SlipBean> findRangedSlipList(HashMap<String, Object> map) {
		return slipApplicationService.findRangedSlipList(map);
	}



	@Override
	public String hrAddSlip(Map<String, ArrayList<SlipBean>> batchArray) {
		return slipApplicationService.hrAddSlip(batchArray);
	}

	//분개


	@Override
	public ArrayList<JournalBean> findRangedJournalList(String fromDate, String toDate) {

		return slipApplicationService.findRangedJournalList(fromDate, toDate);
	}

	@Override
	public ArrayList<JournalBean> findSingleJournalList(String slipNo) {
		return slipApplicationService.findSingleJournalList(slipNo);
	}

	//  분개삭제 생성 2020-09-07
	@Override
	public void deleteJournalRow(String slipNo, String journalNo) {
		slipApplicationService.deleteJournalRow(journalNo);

	}

	//  분개 update 2020-09-12
	@Override
	public void updateJournalList(HashMap<String, ArrayList<JournalBean>> journalList) {

		slipApplicationService.updateJournalList(journalList);
	}



	@Override
	public ArrayList<JournalDoubleBean> findJournalDoubleList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return slipApplicationService.findJournalDoubleList(fromDate, toDate);
	}

	//분개상세


	@Override
	public void addJournalDetailList(String journalNo) {

		slipApplicationService.addJournalDetailList(journalNo);

	}

	@Override
	public void editJournalDetail(JournalDetailBean journalDetailBean) {

		slipApplicationService.editJournalDetail(journalDetailBean);

	}



	@Override
	public ArrayList<JournalDetailBean> getJournalDetailList(String journalNo) {
		System.out.println("나오나?" + journalNo);
		return slipApplicationService.getJournalDetailList(journalNo);
	}

}
