package kr.co.seoulit.erp.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.statement.to.MonthCostStatementBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonthCostStatementDAO {

    // 기간별원가명세서 조회
    ArrayList<MonthCostStatementBean> selectMonthCostList(HashMap<String, String> param);
}
