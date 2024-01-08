package kr.co.seoulit.erp.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.statement.to.MonthIncomeStatementBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonthIncomeStatementDAO {
//    public HashMap<String, Object> selectMonthIncomeStatement(HashMap<String, Object> param);
    public ArrayList<MonthIncomeStatementBean> selectMonthIncomeStatement(HashMap<String, Object> param);
}
