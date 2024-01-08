package kr.co.seoulit.erp.account.statement.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.base.to.PeriodBean;
import kr.co.seoulit.erp.account.statement.to.*;

public interface StatementServiceFacade {
	public HashMap<String, Object> addEarlyStatements(String toDate);

	public HashMap<String, Object> getTotalTrialBalance(String toDate);

//	public HashMap<String, Object> getFinancialPosition(String toDate);

//	public HashMap<String, Object> getIncomeStatement(String toDate);

//	public HashMap<String, Object> getMonthIncomeStatement(String toDate);

	public HashMap<String, Object> getIncomeStatement(String accountPeriodNo, String callResult);

	public HashMap<String, Object> getFinancialPosition(String accountPeriodNo, String callResult);

	public ArrayList<CashJournalBean> getCashJournal(String fromDate, String toDate);

	public ArrayList<DetailTrialBalanceBean> getDetailTrialBalance(String fromDate, String toDate);

	public HashMap<String, Object> getCostStatement(String toDate);

	public HashMap<String, Object> getCashFlowStatement(String toDate);

	public ArrayList<MonthIncomeStatementBean> getMonthIncomeStatement(String accountPeriodNo);

	//회계 기수 조회
	public ArrayList<PeriodBean> findAccountPeriodList();

	public HashMap<String, Object> getTotalTrialBalanceList(String accountPeriodNo, String callResult);

//	public ArrayList<TotalTrialBalance1Bean> getTotalTrialBalanceList(String accountPeriodNo, String callResult);

	public HashMap<String, Object> findEarlyStatements(String accountPeriodNo, String callResult, String periodStartDate);

	public HashMap<String, Object> findEarlyTotaltrialStatement(String accountPeriodNo);

	public ArrayList<TotalTrialBalance1Bean> findEarlyTotaltrialList(String accountPeriodNo);

	public HashMap<String, Object> saveStatementList(String accountPeriodNo, String callResult, String periodStartDate);

	public HashMap<String, Object> cancelStatement(String accountPeriodNo, String callResult);

	//이익잉여금처분계산서
	public ArrayList<RetainedEarningsStatementBean> getRetainedEarningsStatement(String toDate);

	//기간별원가명세서
	public ArrayList<MonthCostStatementBean> getMonthCostStatement(String toDate);

}
