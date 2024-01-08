package kr.co.seoulit.erp.logistic.sales.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.seoulit.erp.logistic.sales.servicefacade.SalesServiceFacade;
import kr.co.seoulit.erp.logistic.sales.to.ContractInfoTO;
import kr.co.seoulit.erp.logistic.sales.to.DeliveryInfoTO;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/logi/sales/*", produces = "application/json")
public class DeliveryController {

	@Autowired
	private SalesServiceFacade salesSF;

	private ModelMap modelMap = new ModelMap();

	@RequestMapping("/searchDeliveryInfoList")
	public ModelMap searchDeliveryInfoList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		try {

			ArrayList<DeliveryInfoTO> deliveryInfoList = salesSF.getDeliveryInfoList();

			modelMap.put("gridRowJson", deliveryInfoList);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;
	}

	// batchListProcess

	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {

//		String batchList = request.getParameter("batchList");

		try {

//			List<DeliveryInfoTO> deliveryTOList = gson.fromJson(batchList, new TypeToken<ArrayList<DeliveryInfoTO>>() {
//			}.getType());

//			HashMap<String, Object> resultMap = salesSF.batchDeliveryListProcess(deliveryTOList);
//
//			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;
	}

	@RequestMapping("/searchDeliverableContractList")
	public ModelMap searchDeliverableContractList(@RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String searchCondition, @RequestParam String customerCode) {

		try {

			ArrayList<ContractInfoTO> deliverableContractList = null;

			if (searchCondition.equals("searchByDate")) {

				String[] paramArray = { startDate, endDate };
				deliverableContractList = salesSF.getDeliverableContractList("searchByDate", paramArray);

			} else if (searchCondition.equals("searchByCustomer")) {

				String[] paramArray = { customerCode };
				deliverableContractList = salesSF.getDeliverableContractList("searchByCustomer", paramArray);

			}

			modelMap.put("gridRowJson", deliverableContractList);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;
	}

	/********************* 납품 2020-12-03 이숭규 **********************/
	@RequestMapping("/deliver")
	public HashMap<String, String> deliver(@RequestParam String contractDetailNo) {

		HashMap<String, Object> resultMap = new HashMap<>(); // object 그대로 받으면 한글 깨짐

		HashMap<String, String> resultMap2 = new HashMap<>();

		try {

			resultMap = salesSF.deliver(contractDetailNo);

			resultMap2.put("errorCode", resultMap.get("errorCode") + "");
			resultMap2.put("errorMsg", (String) resultMap.get("errorMsg"));

		} catch (Exception e2) {
			e2.printStackTrace();

			resultMap2.put("errorCode", "-2");
			resultMap2.put("errorMsg", e2.getMessage());

		}

		return resultMap2;
	}

	// ==========================================박미노=====================================
	@RequestMapping("/deliverDivisionUpdate")
	public void deliverDivisionUpdate(@RequestBody Map<String, ArrayList<DeliveryInfoTO>> deliverUpdate) {

		HashMap<String, Object> resultMap = new HashMap<>();

		System.out.println("de@@@@@@@liverUpdate+deliverUpdate::::" + deliverUpdate);

		try {
			salesSF.deliverUpdate(deliverUpdate);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}

	}
}
