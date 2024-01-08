package kr.co.seoulit.erp.account.budget.controller;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.budget.applicationservice.JpaBudgetService;
import kr.co.seoulit.erp.account.budget.entity.BudgetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.budget.servicefacade.BudgetServiceFacade;
import kr.co.seoulit.erp.account.budget.to.BudgetBean;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/*")
public class BudgetController {

	@Autowired
	private BudgetServiceFacade budgetServiceFacade;

	@Autowired
	private JpaBudgetService jpaBudgetService;

	/*예산신청조회*/
//	@RequestMapping(value = "/budget/findBudget", method = RequestMethod.GET)
//	public HashMap<String, Object> findBudget(BudgetBean budgetData) {
//		System.out.println("뒷단 연결...");
//		System.out.println("값 들어왔나.."+budgetData);
//		HashMap<String, Object> map=new HashMap<>();
//		ArrayList<BudgetBean> findBudgetList = budgetServiceFacade.findBudget(budgetData);
//		map.put("findBudgetList",findBudgetList );
//		return map;
//	}

	@RequestMapping(value = "/budget/findBudget")
	public HashMap<String, Object> findBudget(@RequestParam("deptCode") String deptCode,
											  @RequestParam("workplaceCode") String workplaceCode,
											  @RequestParam("budgetingCode") String budgetingCode,
											  @RequestParam("accountPeriodNo") String accountPeriodNo,
											  @RequestParam("accountInnerCode") String accountInnerCode) {
		System.out.println("뒷단 연결...");
		System.out.println("deptCode 찍힘?"+deptCode);
		HashMap<String, Object> map=new HashMap<>();
		ArrayList<BudgetBean> findBudgetList = budgetServiceFacade.findBudget(deptCode,workplaceCode,budgetingCode,accountPeriodNo,accountInnerCode);
		map.put("findBudgetList",findBudgetList );
		System.out.println(map);
		return map;
	}

//	@RequestMapping(value = "/budget/findBudgetAppl", method = RequestMethod.POST)
//	public ArrayList<BudgetBean> findBudgetAppl(@RequestBody BudgetBean budgetData) {
//
////		return budgetServiceFacade.findBudget(budgetData);
//	}



	@RequestMapping(value = "/budget/findBudgetComparison", method = RequestMethod.POST)
	public HashMap<String, Object> findBudgetComparison(@RequestBody BudgetBean budgetData) {

		return budgetServiceFacade.findBudgetComparison(budgetData);
	}

	@RequestMapping(value = "/budget/findBudgetComparisonStatus", method = RequestMethod.POST)
	public HashMap<String, Object> findBudgetComparisonStatus(@RequestBody BudgetBean budgetData) {

		return budgetServiceFacade.findBudgetComparisonStatus(budgetData);
	}

	/*예산 실적 대비 조회 수정*/
	@RequestMapping("/budget/comparisonBudget")
	public HashMap<String, Object> findComparisonBudget(@RequestParam("deptCode") String deptCode,
														@RequestParam("workplaceCode") String workplaceCode,
														@RequestParam("accountPeriodNo") String accountPeriodNo,
														@RequestParam("accountInnerCode") String accountInnerCode) {
		System.out.println("뒷단 연결");
		System.out.println("deptCode 왔는가"+deptCode+workplaceCode+accountInnerCode+accountPeriodNo);

		HashMap<String, Object> param = new HashMap<>();
		BudgetBean budgetData=new BudgetBean();
		budgetData.setDeptCode(deptCode);
		budgetData.setWorkplaceCode(workplaceCode);
		budgetData.setAccountInnerCode(accountInnerCode);
		budgetData.setAccountPeriodNo(accountPeriodNo);

		try{
			param=budgetServiceFacade.findComparisonBudget(budgetData);
		}catch (Exception e) {
			param.put("errorCode", -1);
			param.put("errorMsg", e.getMessage());
		}
		System.out.println("실적대비 프로시저 값"+ param);
		return param;
	}
//	@RequestMapping(value = "/budget/budgetComparison", method = RequestMethod.GET)
//	public HashMap<String, Object> findBudgetComparisonStatus(@RequestParam("deptCode") String deptCode,
//															  @RequestParam("workplaceCode") String workplaceCode,
//															  @RequestParam("accountPeriodNo") String accountPeriodNo,
//															  @RequestParam("accountInnerCode") String accountInnerCode) {
//
//		System.out.println("뒷단 연결");
//		System.out.println("deptCode 왔는가"+deptCode+workplaceCode+accountInnerCode+accountPeriodNo);
//		ArrayList<BudgetBean> budgetComparisonList =budgetServiceFacade.findBudgetComparisonStatus(deptCode,workplaceCode,accountPeriodNo,accountInnerCode);
//		HashMap<String, Object> map=new HashMap<>();
//		map.put("budgetComparisonList",budgetComparisonList);
//		System.out.println( 나와"+map);
//		return map;
////		return budgetServiceFacade.findBudgetComparisonStatus(deptCode,workplaceCode,accountPeriodNo,accountInnerCode);
//	}

	@RequestMapping(value = "/budget/findbudgetExcessStatus", method = RequestMethod.POST)
	public HashMap<String, Object> findbudgetExcessStatus(@RequestBody BudgetBean budgetData) {

		return budgetServiceFacade.findbudgetExcessStatus(budgetData);
	}

	@RequestMapping(value = "/budget/orgBudget", method = RequestMethod.POST)
	public HashMap<String, Object> orgBudget(@RequestBody BudgetBean budgetData) {

		HashMap<String, Object> param = new HashMap<>();

		try {
			param = budgetServiceFacade.orgBudget(budgetData);
		} catch (Exception e) {
			param.put("errorCode", -1);
			param.put("errorMsg", e.getMessage());
		}
		return param;
	}

//	@RequestMapping(value = "/budget/callBudgetStatus", method = RequestMethod.POST)
//	public HashMap<String, Object> callBudgetStatus(@RequestBody BudgetBean budgetData) {
//
//		HashMap<String, Object> param = new HashMap<>();
//
//		try {
//			param = budgetServiceFacade.callBudgetStatus(budgetData);
//		} catch (Exception e) {
//			param.put("errorCode", -1);
//			param.put("errorMsg", e.getMessage());
//		}
//		return param;
//	}
	/*예산 실적 조회 은비 수정*/
		@RequestMapping(value = "/budget/callBudgetStatus", method = RequestMethod.GET)
	public HashMap<String, Object> callBudgetStatus(@RequestParam("deptCode") String deptCode,
													@RequestParam("workplaceCode") String workplaceCode,
													@RequestParam("accountPeriodNo") String accountPeriodNo) {

		System.out.println("연결됐음?");
		System.out.println("deptCode"+deptCode);

		HashMap<String, Object> param = new HashMap<>();
		BudgetBean budgetData=new BudgetBean();
		budgetData.setAccountPeriodNo(accountPeriodNo);
		budgetData.setDeptCode(deptCode);
		budgetData.setWorkplaceCode(workplaceCode);

		try {
			param = budgetServiceFacade.callBudgetStatus(budgetData);
		} catch (Exception e) {
			param.put("errorCode", -1);
			param.put("errorMsg", e.getMessage());
		}
			System.out.println("실적조회 프로시저 값"+param);
		return param;
	}

	/*예산 신청 저장 은비 수정*/
	@PostMapping("/budget/registerBudget")
	public void registerBudget(@RequestBody BudgetEntity budgetEntity){
		System.out.println("신청 연결 성공?");
		jpaBudgetService.save(budgetEntity);
	};

}
