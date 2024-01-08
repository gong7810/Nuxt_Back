package kr.co.seoulit.erp.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.erp.account.base.to.PeriodBean;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.account.statement.to.TotalTrialBalance1Bean;


@Mapper
public interface TotalTrialBalanceDAO {

	public HashMap<String, Object> addEarlyStatements(String toDate);
    public List<TotalTrialBalance1Bean> callTotalTrialBalance(HashMap<String,Object> param);

    public ArrayList<PeriodBean> selectAccountPeriodList();

    public HashMap<String, Object> selectcallTotalTrialBalance(HashMap<String, Object> param);

//  public ArrayList<TotalTrialBalance1Bean> selectcallTotalTrialBalance(HashMap<String, Object> param);

    public HashMap<String, Object> selectcallEarlyStatements(HashMap<String, Object> param);

    public HashMap<String, Object> callEarlyTotaltrialStatement(HashMap<String, Object> param);

    public ArrayList<TotalTrialBalance1Bean> selectEarlyTotaltrialLists(HashMap<String, Object> param);


    public HashMap<String, Object> callSaveStatementList(HashMap<String, Object> param);

    public HashMap<String, Object> callCancelStatement(HashMap<String, Object> param);


//    public void saveStatementList(String accountPeriodNo);


}
