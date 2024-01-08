package kr.co.seoulit.erp.account.slip.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import kr.co.seoulit.erp.account.slip.to.ReceiptBean;
import kr.co.seoulit.erp.account.slip.to.SlipBean;

@Mapper
public interface SlipDAO {

    public ArrayList<SlipBean> selectSlipDataList(String slipDate);

    public void updateSlip(SlipBean slipBean);

    public void insertSlip(SlipBean slipBean);

    void approveSlip(SlipBean slipBean);

    public ArrayList<SlipBean> selectRangedSlipList(HashMap<String,Object> map);

    public ArrayList<SlipBean> selectDisApprovalSlipList();

    public int selectSlipCount(String today);

    // 전표 전체조회
    public ArrayList<SlipBean> fetchSlip();

    // 승인 / 반려 전표 조회
    public ArrayList<SlipBean> fetchStatusSlipList(String status);

    // 미결 전표 조회
    public ArrayList<SlipBean> fetchUnProSlipList();

    // 전표 한개조회
    public Optional<SlipBean> fetchSlipBySlipNo(String slipNo);

    // 마지막 전표번호 조회
    public String selectSlipMaxNo(String today);

    // 영수증 증빙상태 변경시 전표도 같이 수정
    public void updateAuthorizationSlip(ReceiptBean receiptBean);
}
