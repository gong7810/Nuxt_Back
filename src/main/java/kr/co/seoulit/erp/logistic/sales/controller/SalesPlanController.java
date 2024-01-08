package kr.co.seoulit.erp.logistic.sales.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.seoulit.erp.logistic.sales.servicefacade.SalesServiceFacade;
import kr.co.seoulit.erp.logistic.sales.to.SalesPlanTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/logi/sales/*")
public class SalesPlanController {

	@Autowired
	private SalesServiceFacade salesSF;

	private ModelMap modelMap = new ModelMap();

	@RequestMapping("/searchSalesPlan")
	public ModelMap searchSalesPlanInfo(@RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String salesPlanDate) {

		System.out.println(salesPlanDate);

		try {

			ArrayList<SalesPlanTO> salesPlanTOList = salesSF.getSalesPlanList(salesPlanDate, startDate, endDate);

			modelMap.put("gridRowJson", salesPlanTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;
	}

	@RequestMapping(value = "/batchSalesPlanListProcess", method = RequestMethod.POST)
	public void batchListProcess(@RequestBody SalesPlanTO params) {
		System.out.println(params);
		salesSF.batchSalesPlanListProcess(params);
	}
}
