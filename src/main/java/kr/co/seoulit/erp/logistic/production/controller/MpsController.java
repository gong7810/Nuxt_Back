package kr.co.seoulit.erp.logistic.production.controller;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.seoulit.erp.sys.to.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import kr.co.seoulit.erp.logistic.production.servicefacade.ProductionServiceFacade;
import kr.co.seoulit.erp.logistic.production.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.erp.logistic.production.to.MpsTO;
import kr.co.seoulit.erp.logistic.production.to.SalesPlanInMpsAvailableTO;

import static kr.co.seoulit.erp.sys.to.response.Response.success;

@SuppressWarnings("unused")
@CrossOrigin("*")
@RestController
@RequestMapping("/logi/production/*")
public class MpsController {

	@Autowired
	private ProductionServiceFacade productionSF;

	private ModelMap modelMap = new ModelMap();

	private Gson gson = new Gson();

	@RequestMapping("/searchMpsInfo")
	public Response searchMpsInfo(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String includeMrpApply = request.getParameter("includeMrpApply");
		System.out.println("넘어온 시작날짜::::::::" + startDate);
		System.out.println("넘어온 종료날짜::::::::" + endDate);
		System.out.println("넘어온 ???::::::::" + includeMrpApply);

		try {

			ArrayList<MpsTO> mpsTOList = productionSF.getMpsList(startDate, endDate, includeMrpApply);

			modelMap.put("gridRowJson", mpsTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return success(modelMap);
	}

	@RequestMapping("/searchContractDetailInMpsAvailable")
	@ResponseStatus(HttpStatus.OK)
	public Response searchContractDetailListInMpsAvailable(HttpServletRequest request, HttpServletResponse response) {

		String searchCondition = request.getParameter("searchCondition");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		try {

			ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList = productionSF
					.getContractDetailListInMpsAvailable(searchCondition, startDate, endDate);
			// contractDate, 2019-07-01, 2019-07-31
			modelMap.put("gridRowJson", contractDetailInMpsAvailableList);
		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return success(modelMap);
	}

	@RequestMapping("/searchSalesPlanInMpsAvailable")
	public ModelMap searchSalesPlanListInMpsAvailable(HttpServletRequest request, HttpServletResponse response) {

		String searchCondition = request.getParameter("searchCondition");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		try {

			ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList = productionSF
					.getSalesPlanListInMpsAvailable(searchCondition, startDate, endDate);

			modelMap.put("gridRowJson", salesPlanInMpsAvailableList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping(value = "/convertContractDetailToMps", method = RequestMethod.POST) // mps등록
	@ResponseStatus(HttpStatus.CREATED)
	public Response convertContractDetailToMps(@RequestBody ContractDetailInMpsAvailableTO contract) {

		System.out.println(":::::::::::::::MPS등록 메서드 들어옴:::::::::::::::");

		// String batchList = request.getParameter("batchList");
		System.out.println("MPS등록할 값:::::::::::::::" + contract);
		// ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList =
		// gson.fromJson(batchList, new
		// TypeToken<ContractDetailInMpsAvailableTO>(){}.getType());
		// System.out.println("gson.formJson을 통해 변환된
		// 값:::::::::::::::::::::::::"+contractDetailInMpsAvailableList);
		try {
			System.out.println("contract.getPlanClassification()");
			System.out.println(contract.getPlanClassification());
			HashMap<String, Object> resultMap = productionSF.convertContractDetailToMps(contract);

			modelMap.put("result", resultMap);

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return success(modelMap);
	}

	@RequestMapping("/convertSalesPlanToMps")
	public ModelMap convertSalesPlanToMps(HttpServletRequest request, HttpServletResponse response) {

//		String batchList = request.getParameter("batchList");

//		ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList = gson.fromJson(batchList,
//				new TypeToken<ArrayList<SalesPlanInMpsAvailableTO>>() {
//				}.getType());

		try {

//			HashMap<String, Object> resultMap = productionSF.convertSalesPlanToMps(salesPlanInMpsAvailableList);
//
//			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

}
