package kr.co.seoulit.erp.account.funds.applicationservice;

import kr.co.seoulit.erp.account.funds.repository.FundRepository;
import kr.co.seoulit.erp.account.funds.to.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.erp.account.funds.dao.FundDAO;

import java.util.ArrayList;
import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class FundApplicationServiceImpl implements FundApplicationService {

    @Autowired
    private FundDAO fundDAO;
    @Autowired
    private FundRepository fundRepository;

    // 일자별자금계획 추가
    @Override
    public String registerPlan(PlanBean planBean) {

        StringBuffer planNo = new StringBuffer();
        String planNoDate = planBean.getPlanDate().replace("-", "");
        planNo.append(planNoDate);
        planNo.append("PLAN"); // 20231221PLAN

        String lastNo = fundDAO.getPlanMaxNo(planNoDate);
        if (lastNo == null){
            lastNo = "00000";
        }
        int length = lastNo.length();

        String code = "0000" + (Integer.parseInt(lastNo.substring(length - 5)) + 1) + "";
        planNo.append(code.substring(code.length() - 5)); // 20231116SLIP00001
        planBean.setPlanNo(planNo.toString());

        fundRepository.save(planBean);

        return planNo.toString();
    }

    // 일자별자금계획 조회
    @Override
    public HashMap<String, ArrayList<PlanBean>> getDailyFundPlan(String startDate, String endDate) {
        HashMap<String, ArrayList<PlanBean>> param = new HashMap<>();

        HashMap<String, Object> inData = new HashMap<>();
        inData.put("balanceDivision", "수입");
        inData.put("startDate", startDate);
        inData.put("endDate", endDate);

        HashMap<String, Object> outData = new HashMap<>();
        outData.put("balanceDivision", "지출");
        outData.put("startDate", startDate);
        outData.put("endDate", endDate);

        ArrayList<PlanBean> inExpectedPlanList = fundDAO.getDailyFundPlan(inData);
        ArrayList<PlanBean> outExpectedPlanList = fundDAO.getDailyFundPlan(outData);

        param.put("inExpectedPlanList", inExpectedPlanList);
        param.put("outExpectedPlanList", outExpectedPlanList);

        return param;
    }

    // 일자별자금계획 삭제
    @Override
    public void deleteDailyFundPlan(String planNo) {
        fundRepository.deleteById(planNo);
    }

    // 일자별자금계획 수정
    @Override
    public void updateDailyFundPlan(PlanBean planBean) {
        fundDAO.updateDailyFundPlan(planBean);
    }

    // 자금계획 카렌다 날짜별 수입조회
    @Override
    public String getCalendarIncome(String day) {
        return fundDAO.getCalendarIncome(day);
    }

    // 자금계획 카렌다 날짜별 지출조회
    @Override
    public String getCalendarExpenditure(String day) {
        return fundDAO.getCalendarExpenditure(day);
    }

    // 자금계획 카렌다 날짜별 계획상세조회
    @Override
    public HashMap<String, ArrayList<PlanBean>> getCalendarDetail(String date) {
        HashMap<String, ArrayList<PlanBean>> param = new HashMap<>();

        HashMap<String, Object> inData = new HashMap<>();
        inData.put("balanceDivision", "수입");
        inData.put("startDate", date);
        inData.put("endDate", date);

        HashMap<String, Object> outData = new HashMap<>();
        outData.put("balanceDivision", "지출");
        outData.put("startDate", date);
        outData.put("endDate", date);

        ArrayList<PlanBean> inExpectedPlanList = fundDAO.getDailyFundPlan(inData);
        ArrayList<PlanBean> outExpectedPlanList = fundDAO.getDailyFundPlan(outData);

        param.put("inExpectedPlanList", inExpectedPlanList);
        param.put("outExpectedPlanList", outExpectedPlanList);

        return param;
    }

    // 일일거래증감현황 조회
    @Override
    public HashMap<String,Object> getDailyTradeStatus(String selectDate) {
        HashMap<String,Object> param = new HashMap<>();

        param.put("selectDate", selectDate);
        fundDAO.getDailyTradeStatus(param);

        return param;
    }

    // 입출금예정액 조회
    @Override
    public HashMap<String, ArrayList<InoutBean>>
    getInoutExpectedPrice(String selectDate) {
        HashMap<String, ArrayList<InoutBean>> param = new HashMap<>();

        HashMap<String, String> leftData = new HashMap<>();
        leftData.put("selectDate", selectDate);
        leftData.put("balanceDivision", "차변");
        HashMap<String, String> rightData = new HashMap<>();
        rightData.put("selectDate", selectDate);
        rightData.put("balanceDivision", "대변");

        ArrayList<InoutBean> inExpectedPriceList = fundDAO.getInoutExpectedPrice(leftData);
        ArrayList<InoutBean> outExpectedPriceList = fundDAO.getInoutExpectedPrice(rightData);

        param.put("inExpectedPriceList", inExpectedPriceList);
        param.put("outExpectedPriceList", outExpectedPriceList);

        return param;
    }

    // 예적금현황 조회
    @Override
    public HashMap<String, Object> getFinanceStatus(String selectDate, String selectAccount) {
        HashMap<String, Object> param = new HashMap<>();

        param.put("selectDate", selectDate);
        param.put("selectAccount", selectAccount);

        fundDAO.getFinanceStatus(param);

        return param;
    }

    // 총괄거래현황 조회
    @Override
    public HashMap<String, Object> getGeneralFundStatus(String startDate, String endDate) {
        HashMap<String, Object> param = new HashMap<>();

        HashMap<String, String> selectDate = new HashMap<>();
        selectDate.put("startDate", startDate);
        selectDate.put("endDate", endDate);

        ArrayList<GeneralFundBean> generalFundStatusList = fundDAO.getGeneralFundStatus(selectDate);

        param.put("generalFundStatusList", generalFundStatusList);

        return param;
    }
}
