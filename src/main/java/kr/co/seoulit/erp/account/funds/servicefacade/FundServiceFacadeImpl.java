package kr.co.seoulit.erp.account.funds.servicefacade;

import kr.co.seoulit.erp.account.funds.applicationservice.FundApplicationService;
import kr.co.seoulit.erp.account.funds.to.InoutBean;
import kr.co.seoulit.erp.account.funds.to.PlanBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FundServiceFacadeImpl implements FundServiceFacade{

    @Autowired
    private FundApplicationService fundApllicationService;

    // 일자별자금계획 추가
    @Override
    public String registerPlan(PlanBean planBean) {
        return fundApllicationService.registerPlan(planBean);
    }

    // 일자별자금계획 조회
    @Override
    public HashMap<String, ArrayList<PlanBean>> getDailyFundPlan(String startDate, String endDate) {
        return fundApllicationService.getDailyFundPlan(startDate, endDate);
    }

    // 일자별자금계획 삭제
    @Override
    public void deleteDailyFundPlan(String planNo) {
        fundApllicationService.deleteDailyFundPlan(planNo);
    }

    // 일자별자금계획 수정
    @Override
    public void updateDailyFundPlan(PlanBean planBean) {
        fundApllicationService.updateDailyFundPlan(planBean);
    }

    // 자금계획 카렌다 날짜별 수입조회
    @Override
    public String getCalendarIncome(String day) {
        return fundApllicationService.getCalendarIncome(day);
    }

    // 자금계획 카렌다 날짜별 지출조회
    @Override
    public String getCalendarExpenditure(String day) {
        return fundApllicationService.getCalendarExpenditure(day);
    }

    // 자금계획 카렌다 날짜별 계획상세조회
    @Override
    public HashMap<String, ArrayList<PlanBean>> getCalendarDetail(String date) {
        return fundApllicationService.getCalendarDetail(date);
    }

    // 일일거래증감현황 조회
    @Override
    public HashMap<String,Object> getDailyTradeStatus(String date) {
        return fundApllicationService.getDailyTradeStatus(date);
    }

    // 입출금예정액 조회
    @Override
    public HashMap<String, ArrayList<InoutBean>> getInoutExpectedPrice(String date) {
        return fundApllicationService.getInoutExpectedPrice(date);
    }

    // 예적금현황 조회
    @Override
    public HashMap<String, Object> getFinanceStatus(String date, String accountName) {
        return fundApllicationService.getFinanceStatus(date, accountName);
    }

    // 총괄거래현황 조회
    @Override
    public HashMap<String, Object> getGeneralFundStatus(String startDate, String endDate) {
        return fundApllicationService.getGeneralFundStatus(startDate, endDate);
    }
}
