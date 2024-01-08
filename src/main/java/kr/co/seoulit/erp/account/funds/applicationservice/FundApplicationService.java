package kr.co.seoulit.erp.account.funds.applicationservice;

import kr.co.seoulit.erp.account.funds.to.InoutBean;
import kr.co.seoulit.erp.account.funds.to.PlanBean;

import java.util.ArrayList;
import java.util.HashMap;

public interface FundApplicationService {

    // 일자별자금계획 추가
    String registerPlan(PlanBean planBean);

    // 일자별자금계획 조회
    HashMap<String, ArrayList<PlanBean>> getDailyFundPlan(String startDate, String endDate);

    // 일자별자금계획 삭제
    void deleteDailyFundPlan(String planNo);

    // 일자별자금계획 수정
    void updateDailyFundPlan(PlanBean planBean);

    // 자금계획 카렌다 날짜별 수입조회
    String getCalendarIncome(String day);

    // 자금계획 카렌다 날짜별 지출조회
    String getCalendarExpenditure(String day);

    // 자금계획 카렌다 날짜별 계획상세조회
    HashMap<String, ArrayList<PlanBean>> getCalendarDetail(String date);

    // 일일거래증감현황 조회
    HashMap<String,Object> getDailyTradeStatus(String date);

    // 입출금예정액 조회
    HashMap<String, ArrayList<InoutBean>> getInoutExpectedPrice(String date);

    // 예적금현황 조회
    HashMap<String, Object> getFinanceStatus(String date, String accountName);

    // 총괄거래현황 조회
    HashMap<String, Object> getGeneralFundStatus(String startDate, String endDate);

}