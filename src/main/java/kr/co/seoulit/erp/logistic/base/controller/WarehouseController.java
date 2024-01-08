package kr.co.seoulit.erp.logistic.base.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.seoulit.erp.logistic.base.servicefacade.LogisticsInfoServiceFacade;
import kr.co.seoulit.erp.logistic.base.to.WarehouseTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/logi/base/*")
public class WarehouseController {

	@Autowired
	private LogisticsInfoServiceFacade logisticsSF;

	private ModelMap modelMap = new ModelMap();

	@RequestMapping("/warehouseInfo")
	public ModelMap getWarehouseList(HttpServletRequest request, HttpServletResponse response) {

		try {
			ArrayList<WarehouseTO> WarehouseTOList = logisticsSF.getWarehouseInfoList();
			System.out.println(WarehouseTOList);

			modelMap.put("gridRowJson", WarehouseTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");
		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/warehousebatchListProcess", method = RequestMethod.POST)
//   public ModelMap warehousebatchListProcess(@RequestBody Map<String, ArrayList<WarehouseTO>> batchList) {
	public ModelMap warehousebatchListProcess(@RequestBody ArrayList<WarehouseTO> batchList) {

		System.out.println("@@@@@@@@@@@warehouseBatch");
		System.out.println(batchList);

//      ArrayList<WarehouseTO> warehouseList = batchList.get("warehouseInfo");
		HashMap<String, Object> resultMap = null;

		try {
			resultMap = logisticsSF.batchWarehouseInfoProcess(batchList);
			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");
		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

}