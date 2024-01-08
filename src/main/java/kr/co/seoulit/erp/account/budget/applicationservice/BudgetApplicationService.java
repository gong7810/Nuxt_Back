package kr.co.seoulit.erp.account.budget.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.budget.to.BudgetBean;

public interface BudgetApplicationService {

	public ArrayList<BudgetBean> findBudget(String deptCode,String workplaceCode,String budgetingCode,String accountPeriodNo,String accountInnerCode);

	public HashMap<String, Object> orgBudget(BudgetBean bean);

	public ArrayList<BudgetBean> findBudgetAppl(BudgetBean bean);

	public HashMap<String, Object> callBudgetStatus(BudgetBean budgetData);

	public HashMap<String, Object> findBudgetComparison(BudgetBean budgetData);

	public HashMap<String, Object> findBudgetComparisonStatus(BudgetBean budgetData);
//	public ArrayList<BudgetBean> findBudgetComparisonStatus(String deptCode,String workplaceCode,String accountPeriodNo,String accountInnerCode);
	public HashMap<String, Object> findbudgetExcessStatus(BudgetBean budgetData);

	public HashMap<String, Object> findComparisonBudget(BudgetBean budgetData);
}
