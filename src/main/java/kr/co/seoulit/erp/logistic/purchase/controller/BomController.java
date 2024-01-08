package kr.co.seoulit.erp.logistic.purchase.controller;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.co.seoulit.erp.logistic.purchase.servicefacade.PurchaseServiceFacade;
import kr.co.seoulit.erp.logistic.purchase.to.BomDeployTO;
import kr.co.seoulit.erp.logistic.purchase.to.BomInfoTO;
import kr.co.seoulit.erp.logistic.purchase.to.BomTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/logi/purchase/*")
public class BomController {

	@Autowired
	private PurchaseServiceFacade purchaseSF;
	private static Gson gson = new GsonBuilder().serializeNulls().create();

	private ModelMap modelMap = new ModelMap();

	@RequestMapping("/searchBomDeploy")
	public ArrayList<BomDeployTO> searchBomDeploy(HttpServletRequest request, HttpServletResponse response) {

		String deployCondition = request.getParameter("deployCondition");
		System.out.println("deployCondition : " + deployCondition);
		// forward 정전개 || reverse 역전개
		String itemCode = request.getParameter("itemCode");
		System.out.println("itemCode : " + itemCode);
		// CodeController를 사용하여 검색한 후 선택하여 텍스트박스에 들어있던 값을 파라미터로 받아옴
		// ex) DK-01
		String itemClassificationCondition = request.getParameter("itemClassificationCondition");
		System.out.println("itemClassificationCondition : " + itemClassificationCondition);

		ArrayList<BomDeployTO> bomDeployList = purchaseSF.getBomDeployList(deployCondition, itemCode,
				itemClassificationCondition);

		return bomDeployList;

	}

	@RequestMapping("/searchBomInfo")
	public ArrayList<BomInfoTO> searchBomInfo(HttpServletRequest request, HttpServletResponse response) {

		String parentItemCode = request.getParameter("parentItemCode");

		ArrayList<BomInfoTO> bomInfoList = purchaseSF.getBomInfoList(parentItemCode);

		return bomInfoList;

	}

	@RequestMapping("/searchAllItemWithBomRegisterAvailable")
	public ModelMap searchAllItemWithBomRegisterAvailable(HttpServletRequest request, HttpServletResponse response) {

		try {

			ArrayList<BomInfoTO> allItemWithBomRegisterAvailable = purchaseSF.getAllItemWithBomRegisterAvailable();

			modelMap.put("gridRowJson", allItemWithBomRegisterAvailable);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;

	}

	@RequestMapping(value = "/batchBomListProcess", method = RequestMethod.POST)
	public HashMap<String, Object> batchBomListProcess(@RequestBody HashMap<String, Object> params) {

		// String batchList = request.getParameter("batchList");
		System.out.println("params : " + params);
		String batchList = (String) params.get("batchList");
		System.out.println("batchList : " + batchList);

		ArrayList<BomTO> batchBomList = gson.fromJson(batchList, new TypeToken<ArrayList<BomTO>>() {
		}.getType());
		System.out.println(batchBomList);
		HashMap<String, Object> resultList = purchaseSF.batchBomListProcess(batchBomList);

		return resultList;

	}

}