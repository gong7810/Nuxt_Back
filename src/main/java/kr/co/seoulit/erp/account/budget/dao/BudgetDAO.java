package kr.co.seoulit.erp.account.budget.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.account.budget.to.BudgetBean;
import kr.co.seoulit.erp.account.budget.to.BudgetComparisonStatusBean;

@Mapper
public interface BudgetDAO {

	public ArrayList<BudgetBean> selectBudget(HashMap<String, Object> map);

	public HashMap<String, Object> orgBudget(BudgetBean bean);

	public ArrayList<BudgetBean> selectBudgetAppl(BudgetBean bean);

	public void callBudgetStatus(HashMap<String, Object> resultMap);

	public ArrayList<BudgetComparisonStatusBean> selectBudgetComparisonStatus(BudgetBean budgetData);

//   public ArrayList<BudgetBean> selectComparisonBudget(String deptCode,String workplaceCode,String accountPeriodNo,String accountInnerCode);

	public HashMap<String, Object> selectComparisonBudget(HashMap<String, Object> resultMap);
}