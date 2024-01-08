package kr.co.seoulit.erp.account.budget.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.erp.account.budget.applicationservice.BudgetApplicationService;
import kr.co.seoulit.erp.account.budget.to.BudgetBean;

@Service
public class BudgetServiceFacadeImpl implements BudgetServiceFacade {

	@Autowired
	private BudgetApplicationService budgetApplicationService;

	@Override
	public ArrayList<BudgetBean> findBudget(String deptCode,String workplaceCode,String budgetingCode,String accountPeriodNo,String accountInnerCode) {
		// TODO Auto-generated method stub
		return budgetApplicationService.findBudget(deptCode,workplaceCode,budgetingCode,accountPeriodNo,accountInnerCode);
	}

	@Override
	public HashMap<String, Object> orgBudget(BudgetBean bean) {
		// TODO Auto-generated method stub
		return budgetApplicationService.orgBudget(bean);
	}

	@Override
	public ArrayList<BudgetBean> findBudgetAppl(BudgetBean bean) {
		// TODO Auto-generated method stub
		return budgetApplicationService.findBudgetAppl(bean);
	}

	@Override
	public HashMap<String, Object> callBudgetStatus(BudgetBean budgetData) {
		// TODO Auto-generated method stub
		return budgetApplicationService.callBudgetStatus(budgetData);
	}

	@Override
	public HashMap<String, Object> findBudgetComparison(BudgetBean budgetData) {
		// TODO Auto-generated method stub
		return budgetApplicationService.findBudgetComparison(budgetData);
	}

	@Override
	public HashMap<String, Object> findBudgetComparisonStatus(BudgetBean budgetData) {
		// TODO Auto-generated method stub
		return budgetApplicationService.findBudgetComparisonStatus(budgetData);
	}

	@Override
	public HashMap<String, Object> findbudgetExcessStatus(BudgetBean budgetData) {
		// TODO Auto-generated method stub
		return budgetApplicationService.findbudgetExcessStatus(budgetData);
	}

	@Override
	public HashMap<String, Object> findComparisonBudget(BudgetBean budgetData) {

		return budgetApplicationService.findComparisonBudget(budgetData);

	}

}
