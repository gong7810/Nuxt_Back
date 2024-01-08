package kr.co.seoulit.erp.logistic.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.logistic.base.servicefacade.LogisticsInfoServiceFacade;
import kr.co.seoulit.erp.logistic.base.to.ItemInfoTO;
import kr.co.seoulit.erp.logistic.base.to.ItemTO;

@SuppressWarnings("unused")
@CrossOrigin("*")
@RestController
@RequestMapping("/logi/base/*")
public class ItemController {

	@Autowired
	private LogisticsInfoServiceFacade logisticsSF;

	private ModelMap modelMap = new ModelMap();

	@RequestMapping(value = "/searchItem", method = RequestMethod.GET)
	public ModelMap searchItem(@RequestParam String searchCondition, @RequestParam String minPrice,
			@RequestParam String maxPrice, @RequestParam String itemClassification,
			@RequestParam String itemGroupCode) {

		System.out.println(searchCondition);
		System.out.println(minPrice);
		System.out.println(maxPrice);
		System.out.println(itemClassification);
		System.out.println(itemGroupCode);
		ArrayList<ItemInfoTO> itemInfoList = null;
		String[] paramArray = null;

		Map<String, String> params = new HashMap<String, String>();
		params.put("searchCondition", searchCondition);
		params.put("minPrice", minPrice);
		params.put("maxPrice", maxPrice);
		params.put("itemClassification", itemClassification);
		params.put("itemGroupCode", itemGroupCode);

		try {

			switch (searchCondition) {

			case "ALL":

				paramArray = null;
				break;

			case "ITEM_CLASSIFICATION":

				paramArray = new String[] { itemClassification };
				break;

			case "ITEM_GROUP_CODE":

				paramArray = new String[] { itemGroupCode };
				break;

			case "STANDARD_UNIT_PRICE":

				paramArray = new String[] { minPrice, maxPrice };
				break;

			}

			itemInfoList = logisticsSF.getItemInfoList(params);

			modelMap.put("gridRowJson", itemInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@GetMapping("/getStandardUnitPrice")
	public ModelMap getStandardUnitPrice(@RequestParam String itemCode) {
		System.out.println("itemCode = " + itemCode);

		int price = 0;

		try {
			price = logisticsSF.getStandardUnitPrice(itemCode);

			modelMap.put("gridRowJson", price);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping(value = "/batchItemListProcess", method = RequestMethod.POST)
	public ModelMap batchListProcess(@RequestBody Map<String, ArrayList<ItemTO>> batchList) {

		ArrayList<ItemTO> itemTOList = batchList.get("batchList");

		try {
			HashMap<String, Object> resultMap = logisticsSF.batchItemListProcess(itemTOList);
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

	@RequestMapping("/getOptionItemList")
	public HashMap<String, Object> getOptionItemList(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("gridRowJson", logisticsSF.getOptionItemList());
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());
		}
		return resultMap;
	}

}
