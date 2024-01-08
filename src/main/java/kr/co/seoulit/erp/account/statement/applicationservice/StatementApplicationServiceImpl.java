package kr.co.seoulit.erp.account.statement.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.base.to.PeriodBean;
import kr.co.seoulit.erp.account.statement.dao.*;
import kr.co.seoulit.erp.account.statement.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatementApplicationServiceImpl implements StatementApplicationService {

	@Autowired
	private TotalTrialBalanceDAO totalTrialBalanceDAO;
	@Autowired
	private FinancialPositionDAO financialPositionDAO;
	@Autowired
	private DetailTrialBalanceDAO detailTrialBalanceDAO;
	@Autowired
	private CashJournalDAO cashJournalDAO;
	@Autowired
	private IncomeStatementDAO IncomeStatementDAO;
	@Autowired
	private CostStatementDAO CostStatementDAO;
	@Autowired
	private CashFlowStatementDAO CashFlowStatementDAO;
	@Autowired
	private MonthIncomeStatementDAO MonthIncomeStatementDAO;
	@Autowired
	private RetainedEarningsStatementDAO retainedEarningsStatementDAO;
	@Autowired
	private MonthCostStatementDAO monthCostStatementDAO;


	@Override
	public HashMap<String, Object> getTotalTrialBalance(String toDate) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("toDate", toDate);
		totalTrialBalanceDAO.callTotalTrialBalance(param);

		return param;

	}


	public HashMap<String, Object> addEarlyStatements(String toDate) {

		return totalTrialBalanceDAO.addEarlyStatements(toDate);

	}

//	@Override
//	public HashMap<String, Object> getFinancialPosition(String toDate) {
//
//		HashMap<String, Object> param = new HashMap<>();
//		param.put("toDate", toDate);
//		financialPositionDAO.callFinancialPosition(param);
//		return param;
//	}

	@Override
	public HashMap<String, Object> getFinancialPosition(String accountPeriodNo, String callResult) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("accountPeriodNo", accountPeriodNo);
		param.put("callResult", callResult);
		financialPositionDAO.selectcallFinancialPosition(param);
		return param;
	}

	@Override
	public ArrayList<CashJournalBean> getCashJournal(String fromDate, String toDate) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("fromDate", fromDate);
		param.put("toDate", toDate);
		return cashJournalDAO.selectCashJournalList(param);

	}

//	@Override
//	public HashMap<String, Object> getIncomeStatement(String toDate) {
//		System.out.println("寃잛씤而�  �뀭:" + toDate);
//		HashMap<String, Object> param = new HashMap<>();
//		param.put("toDate", toDate);
//		IncomeStatementDAO.callIncomeStatement(param);
//		return param;
//
//	}


	@Override
	public HashMap<String, Object> getIncomeStatement(String accountPeriodNo, String callResult) {
		System.out.println("service에 잘넘어오니? :" + accountPeriodNo + " , " + callResult);
		HashMap<String, Object> param = new HashMap<>();
		param.put("accountPeriodNo", accountPeriodNo);
		param.put("callResult", callResult);
		IncomeStatementDAO.callIncomeStatement(param);
		return param;

	}

	@Override
	public ArrayList<DetailTrialBalanceBean> getDetailTrialBalance(String fromDate, String toDate) {

		HashMap<String, String> param = new HashMap<>();
		param.put("fromDate", fromDate);
		param.put("toDate", toDate);
		return detailTrialBalanceDAO.selectDetailTrialBalance(param);

	}


	@Override
	public ArrayList<PeriodBean> findAccountPeriodList() {
		return totalTrialBalanceDAO.selectAccountPeriodList();
	}


	@Override
	public HashMap<String, Object> getTotalTrialBalanceList(String accountPeriodNo, String callResult) {
		HashMap<String, Object> param = new HashMap<>();

		param.put("accountPeriodNo", accountPeriodNo);
		param.put("callResult", callResult);
		totalTrialBalanceDAO.selectcallTotalTrialBalance(param);
		return param;
	}

//
//	@Override
//	public ArrayList<TotalTrialBalance1Bean> getTotalTrialBalanceList(String accountPeriodNo, String callResult) {
//		HashMap<String, Object> param = new HashMap<>();
//		param.put("accountPeriodNo", accountPeriodNo);
//		param.put("callResult", callResult);
//		return totalTrialBalanceDAO.selectcallTotalTrialBalance(param);
//	}


	@Override
	public HashMap<String, Object> getEarlyStatements(String accountPeriodNo, String callResult, String periodStartDate){
		HashMap<String, Object> param = new HashMap<>();

		param.put("accountPeriodNo", accountPeriodNo);
		param.put("callResult", callResult);
		param.put("periodStartDate", periodStartDate);
		totalTrialBalanceDAO.selectcallEarlyStatements(param);
		return param;
	}

	//원가명세서
	@Override
	public HashMap<String, Object> getCostStatement(String toDate) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("toDate", toDate);
		CostStatementDAO.callCostStatement(param);
		System.out.println("params💕💕💕 : " + param);
		return param;
	}

	//---------------현금흐름표
			@Override
			public HashMap<String, Object> getCashFlowStatement(String toDate) {
				HashMap<String, Object> param = new HashMap<>();
				param.put("toDate", toDate);
				CashFlowStatementDAO.callCashFlowStatement(param);
				System.out.println("겟인컴  ㅣ:" + param);
				return param;
			}
	//-------월별손익계산서 시작 --------------------

//	@Override
//	public HashMap<String, Object> getMonthIncomeStatement(String toDate) {
//		HashMap<String, Object> param = new HashMap<>();
//		String year = toDate.substring(0,4);
//		param.put("year", year);
//		MonthIncomeStatementDAO.callMonthIncomeStatement(param);
//		return param;
//	}

//	@Override
//	public HashMap<String, Object> getMonthIncomeStatement(String accountPeriodNo) {
//		HashMap<String, Object> param = new HashMap<>();
//		param.put("accountPeriodNo", accountPeriodNo);
//		MonthIncomeStatementDAO.selectMonthIncomeStatement(param);
//		return param;
//	}
@Override
public ArrayList<MonthIncomeStatementBean> getMonthIncomeStatement(String accountPeriodNo) {
	HashMap<String, Object> param = new HashMap<>();
	param.put("accountPeriodNo", accountPeriodNo);
	return MonthIncomeStatementDAO.selectMonthIncomeStatement(param);
}

	@Override
	public HashMap<String, Object> getEarlyTotaltrialStatement(String accountPeriodNo){
		HashMap<String, Object> param =new HashMap<>();
		param.put("accountPeriodNo", accountPeriodNo);
		System.out.println(param);
		totalTrialBalanceDAO.callEarlyTotaltrialStatement(param);
		return param;
	}

	@Override
	public ArrayList<TotalTrialBalance1Bean> getEarlyTotaltrialLists(String accountPeriodNo){
		HashMap<String, Object> param =new HashMap<>();
		param.put("accountPeriodNo", accountPeriodNo);

		return totalTrialBalanceDAO.selectEarlyTotaltrialLists(param);
	}

//	@Override
//	public void saveStatementList(String accountPeriodNo) {
//		// TODO Auto-generated method stub
//		totalTrialBalanceDAO.saveStatementList(accountPeriodNo);
//	}


	@Override
	public HashMap<String, Object> saveStatementList(String accountPeriodNo, String callResult, String periodStartDate) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("accountPeriodNo", accountPeriodNo);
		param.put("callResult", callResult);
		param.put("periodStartDate", periodStartDate);

		// TODO Auto-generated method stub
		totalTrialBalanceDAO.callSaveStatementList(param);
		return param;
	}

	@Override
	public HashMap<String, Object> cancelStatement(String accountPeriodNo, String callResult){
		HashMap<String, Object> param =new HashMap<>();
		param.put("accountPeriodNo", accountPeriodNo);
		param.put("callResult", callResult);
		totalTrialBalanceDAO.callCancelStatement(param);
		return param;
	};

	// 이익잉여금처분계산서 조회
	@Override
	public ArrayList<RetainedEarningsStatementBean> getRetainedEarningsStatement(String toDate) {
		HashMap<String, String> param = new HashMap<>();
		param.put("toDate", toDate);
		return retainedEarningsStatementDAO.selectRetainedEarningsList(param);
	}

	//기간별원가명세서
	@Override
	public ArrayList<MonthCostStatementBean> getMonthCostStatement(String toDate) {
		HashMap<String, String> param = new HashMap<>();
		param.put("toDate", toDate);
		return monthCostStatementDAO.selectMonthCostList(param);
	}

}
