package kr.co.seoulit.erp.account.statement.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.base.to.PeriodBean;
import kr.co.seoulit.erp.account.statement.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.erp.account.statement.applicationservice.StatementApplicationService;

@Service
public class StatementServiceFacadeImpl implements StatementServiceFacade {

	@Autowired
	private StatementApplicationService statementApplicationService;

	@Override
	public HashMap<String, Object> getTotalTrialBalance(String toDate) {
		return statementApplicationService.getTotalTrialBalance(toDate);
	}

//	@Override
//	public HashMap<String, Object> getIncomeStatement(String toDate) {
//		return statementApplicationService.getIncomeStatement(toDate);
//	}

//	@Override
//	public HashMap<String, Object> getFinancialPosition(String toDate) {
//		return statementApplicationService.getFinancialPosition(toDate);
//	}

//	@Override
//	public HashMap<String, Object> getMonthIncomeStatement(String toDate) {
//		return statementApplicationService.getMonthIncomeStatement(toDate);
//
//	}

	@Override
	public HashMap<String, Object> getIncomeStatement(String accountPeriodNo, String callResult) {
		return statementApplicationService.getIncomeStatement(accountPeriodNo, callResult);
	}

	@Override
	public HashMap<String, Object> getFinancialPosition(String accountPeriodNo, String callResult) {
		return statementApplicationService.getFinancialPosition(accountPeriodNo, callResult);
	}

	@Override
	public ArrayList<CashJournalBean> getCashJournal(String fromDate, String toDate) {
		return statementApplicationService.getCashJournal(fromDate, toDate);
	}

	@Override
	public HashMap<String, Object> addEarlyStatements(String toDate) {
		return statementApplicationService.addEarlyStatements(toDate);
	}

	@Override
	public ArrayList<DetailTrialBalanceBean> getDetailTrialBalance(String fromDate, String toDate) {
		return statementApplicationService.getDetailTrialBalance(fromDate, toDate);
	}

	@Override
	public HashMap<String, Object> getCostStatement(String toDate) {
		// TODO Auto-generated method stub
		return statementApplicationService.getCostStatement(toDate);
	}

	@Override
	public HashMap<String, Object> getCashFlowStatement(String toDate) {
		// TODO Auto-generated method stub
		return statementApplicationService.getCashFlowStatement(toDate);
	}

	@Override
	public ArrayList<MonthIncomeStatementBean> getMonthIncomeStatement(String accountPeriodNo) {
		return statementApplicationService.getMonthIncomeStatement( accountPeriodNo );
	}

	//회계 기수 조회
	@Override
	public ArrayList<PeriodBean> findAccountPeriodList() {
		return statementApplicationService.findAccountPeriodList();
	}

	@Override
	public HashMap<String, Object> getTotalTrialBalanceList(String accountPeriodNo, String callResult){
		System.out.println(accountPeriodNo);
		System.out.println(callResult);

		return statementApplicationService.getTotalTrialBalanceList(accountPeriodNo, callResult);
	}

//	@Override
//	public ArrayList<TotalTrialBalance1Bean> getTotalTrialBalanceList(String accountPeriodNo, String callResult){
//		return statementApplicationService.getTotalTrialBalanceList(accountPeriodNo, callResult);
//	}

	@Override
	public HashMap<String, Object> findEarlyStatements(String accountPeriodNo, String callResult, String periodStartDate){
		return statementApplicationService.getEarlyStatements(accountPeriodNo, callResult, periodStartDate);
	}

	@Override
	public HashMap<String, Object> findEarlyTotaltrialStatement(String accountPeriodNo){
		return statementApplicationService.getEarlyTotaltrialStatement(accountPeriodNo);
	}

	@Override
	public ArrayList<TotalTrialBalance1Bean> findEarlyTotaltrialList(String accountPeriodNo){
		return statementApplicationService.getEarlyTotaltrialLists(accountPeriodNo);
	}

	@Override
	public HashMap<String, Object> saveStatementList(String accountPeriodNo, String callResult, String periodStartDate){
		return statementApplicationService.saveStatementList(accountPeriodNo, callResult, periodStartDate);
	};

	public HashMap<String, Object> cancelStatement(String accountPeriodNo, String callResult){
		return statementApplicationService.cancelStatement(accountPeriodNo, callResult);
	};

	//이익잉여금처분계산서
	@Override
	public ArrayList<RetainedEarningsStatementBean> getRetainedEarningsStatement(String toDate) {
		return statementApplicationService.getRetainedEarningsStatement(toDate);
	}

	//기간별원가명세서
	@Override
	public ArrayList<MonthCostStatementBean> getMonthCostStatement(String toDate) {
		return statementApplicationService.getMonthCostStatement(toDate);
	}
}
