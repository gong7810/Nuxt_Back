package kr.co.seoulit.erp.logistic.production.controller;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.logistic.production.servicefacade.ProductionServiceFacade;
import kr.co.seoulit.erp.logistic.production.to.ProductionPerformanceInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkOrderInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkSiteSimulationTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/logi/production/*")
public class WorkOrderController {

	@Autowired
	private ProductionServiceFacade productionSF;

	private ModelMap modelMap = new ModelMap();

	@RequestMapping("/getWorkOrderableMrpList")
	public HashMap<String, Object> getWorkOrderableMrpList(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = productionSF.getWorkOrderableMrpList();
			System.out.println("resultMap.toString() = " + resultMap.toString());
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

	@RequestMapping("/showWorkOrderDialog")
	public HashMap<String, Object> showWorkOrderDialog(@RequestParam String mrpNo,@RequestParam String mrpGatheringNo) {



		HashMap<String, Object> resultMap = new HashMap<>();

		System.out.println("넘어오니???? 가더노 :"+mrpGatheringNo+"엠알피노: "+mrpNo);

		try {
			resultMap = productionSF.getWorkOrderSimulationList(mrpNo,mrpGatheringNo);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

	@RequestMapping("/workOrder")
	public HashMap<String, Object> workOrder(HttpServletRequest request, HttpServletResponse response) {

		String mrpGatheringNo = request.getParameter("mrpGatheringNo");
		String workPlaceCode = request.getParameter("workPlaceCode");
		String productionProcess = request.getParameter("productionProcessCode");
		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = productionSF.workOrder(mrpGatheringNo, workPlaceCode, productionProcess);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

	@RequestMapping("/showWorkOrderInfoList")
	public ModelMap showWorkOrderInfoList(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<WorkOrderInfoTO> workOrderInfoList = null;

		try {

			workOrderInfoList = productionSF.getWorkOrderInfoList();

			modelMap.put("gridRowJson", workOrderInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", " 꽦怨 ");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/workOrderCompletion")
	public HashMap<String, Object> workOrderCompletion(HttpServletRequest request, HttpServletResponse response) {

		String workOrderNo = request.getParameter("workOrderNo");
		String actualCompletionAmount = request.getParameter("actualCompletionAmount");
		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = productionSF.workOrderCompletion(workOrderNo, actualCompletionAmount);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

	@RequestMapping("/getProductionPerformanceInfoList")
	public ModelMap getProductionPerformanceInfoList(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<ProductionPerformanceInfoTO> productionPerformanceInfoList = null;

		try {

			productionPerformanceInfoList = productionSF.getProductionPerformanceInfoList();

			modelMap.put("gridRowJson", productionPerformanceInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", " 꽦怨 ");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/showWorkSiteSituation")
	public HashMap<String, Object> showWorkSiteSituation(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, Object> resultMap = new HashMap<>();

		String workSiteCourse = request.getParameter("workSiteCourse");
		String workOrderNo = request.getParameter("workOrderNo");
		String itemClassIfication = request.getParameter("itemClassIfication");

		try {

			resultMap = productionSF.showWorkSiteSituation(workSiteCourse, workOrderNo, itemClassIfication);

			resultMap.put("gridRowJson", resultMap.get("result"));
			resultMap.put("errorCode", resultMap.get("errorCode"));
			resultMap.put("errorMsg", resultMap.get("errorMsg"));

			System.out.println("@@@@@");
			System.out.println(resultMap);
			System.out.println(resultMap.get("result"));
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

	@RequestMapping("/workCompletion")
	public ModelMap workCompletion(@RequestBody HashMap<String, ArrayList<WorkSiteSimulationTO>> workOrderInfo) {

//      ArrayList<String> itemCodeListArr = gson.fromJson(itemCodeList,
//            new TypeToken<ArrayList<String>>() {}.getType());
		System.out.println("@@@@" + workOrderInfo);
//      System.out.println(workOrderNo);
//      System.out.println(itemCode);s
		try {

			productionSF.workCompletion(workOrderInfo);

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/workSiteLog")
	public HashMap<String, Object> workSiteLogList(HttpServletRequest request, HttpServletResponse response) {

		String workSiteLogDate = request.getParameter("workSiteLogDate");

		System.out.println(workSiteLogDate);
		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = productionSF.workSiteLogList(workSiteLogDate);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

}
