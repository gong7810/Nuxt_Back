package kr.co.seoulit.erp.account.slip.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.co.seoulit.erp.account.slip.to.*;
import org.springframework.web.multipart.MultipartFile;

public interface SlipApplicationService {

	 //전표 전체조회
	 ArrayList<SlipBean> findSlipList();

	 //승인 / 반려 전표 조회
	 ArrayList<SlipBean> findStatusSlipList(String status);

	 //미결 전표 조회
	 ArrayList<SlipBean> findUnProSlipList();

	 //전표 한개조회
	 SlipBean findSlipBySlipNo(String slipNo);

	 //전표 한개등록
	 String registerSlip(SlipBean slipBean);

	 //전표 리스트등록
	 String registerSlipList(ArrayList<SlipBean> slipList);

	//전표 리스트삭제
	 void deleteSlip(ArrayList<SlipBean> slipList);

	 //전표 리스트승인
	void approveSlip(ArrayList<SlipBean> slipList);

	 //전표수정
	 void updateSlip(SlipBean slipBean);

	 //분개 전체조회
	 ArrayList<JournalBean> findJournalListBySlipNo(String slipNo);

//====================================================================

	//지출증빙 전체조회
	ArrayList<ReceiptBean> findReceiptList();

	//지출증빙 한개조회
	ReceiptBean findReceiptBySlipNo(String slipNo);

	//지출증빙 증빙등록
	void registerReceipt(String slipNo, String type, MultipartFile file);

	//지출증빙 증빙삭제
	void deleteReceiptFile(String slipNo);

	//지출증빙 증빙완료
	void proofReceipt(ArrayList<ReceiptBean> receiptList);




















//====================================================================

	public ArrayList<SlipBean> findSlipDataList(String slipDate);

	public ArrayList<SlipBean> findRangedSlipList(HashMap<String, Object> map);

	public String addSlip(Map<String, SlipBean> batchArray);

	public String hrAddSlip(Map<String, ArrayList<SlipBean>> batchArray);

	//분개
	public ArrayList<JournalBean> findSingleJournalList(String slipNo);

	public ArrayList<JournalBean> findRangedJournalList(String fromDate, String toDate);

	public void deleteJournalRow(String journalNo);

	public void updateJournalList(HashMap<String, ArrayList<JournalBean>> journalList); //  분개 update 2020-09-12


	public ArrayList<JournalDoubleBean> findJournalDoubleList(String fromDate, String toDate);




	//분개상세

	public ArrayList<JournalDetailBean> getJournalDetailList(String journalNo);

	public void addJournalDetailList(String journalNo);

	public void editJournalDetail(JournalDetailBean journalDetailBean);
}


