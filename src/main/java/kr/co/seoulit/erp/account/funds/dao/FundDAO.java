package kr.co.seoulit.erp.account.funds.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.funds.to.GeneralFundBean;
import kr.co.seoulit.erp.account.funds.to.InoutBean;
import kr.co.seoulit.erp.account.funds.to.PlanBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FundDAO {

    // 당일 마지막 계획번호 조회
    public String getPlanMaxNo(String today);

    // 일자별자금계획 조회
    public ArrayList<PlanBean> getDailyFundPlan(HashMap<String, Object> Data);

    // 일자별자금계획 수정
    public void updateDailyFundPlan(PlanBean planBean);

    // 자금계획 카렌다 날짜별 수입조회
    public String getCalendarIncome(String day);

    // 자금계획 카렌다 날짜별 지출조회
    public String getCalendarExpenditure(String day);

    // 일일거래증감현황 조회
    public HashMap<String,Object> getDailyTradeStatus(HashMap<String,Object> param);

    // 입출금예정액 조회
    public ArrayList<InoutBean> getInoutExpectedPrice(HashMap<String, String> data);

    // 예적금현황 조회
    public HashMap<String,Object> getFinanceStatus(HashMap<String, Object> param);

    // 총괄거래현황 조회
    public ArrayList<GeneralFundBean> getGeneralFundStatus(HashMap<String, String> param);

}
