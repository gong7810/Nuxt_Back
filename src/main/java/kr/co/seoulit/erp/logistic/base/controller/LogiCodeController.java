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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.seoulit.erp.logistic.base.servicefacade.LogiBaseServiceFacade;
import kr.co.seoulit.erp.logistic.base.to.LogiCodeDetailTO;
import kr.co.seoulit.erp.logistic.base.to.LogiCodeTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/logi/base/*")
public class LogiCodeController {

	@Autowired
	private LogiBaseServiceFacade baseSF;

	private ModelMap modelMap = new ModelMap();

	@RequestMapping(value = "/searchCodeList", method = RequestMethod.GET)
	public ModelMap findCodeList(HttpServletRequest request, HttpServletResponse response) {

		try {

			ArrayList<LogiCodeTO> codeList = baseSF.getCodeList();

			modelMap.put("codeList", codeList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;

	}

	@RequestMapping(value = "/codeList", method = RequestMethod.GET)
	public ModelMap findDetailCodeList(@RequestParam String divisionCode) {

		System.out.println(" @@@@@divisionCode:: " + divisionCode);
		try {

			ArrayList<LogiCodeDetailTO> detailCodeList = baseSF.getDetailCodeList(divisionCode);
			modelMap.put("detailCodeList", detailCodeList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");
		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/batchListProcess", method = RequestMethod.POST)
	public ModelMap batchListProcess(@RequestBody Map<String, ArrayList<LogiCodeTO>> batchList) {

		System.out.println("aa");
		ArrayList<LogiCodeTO> codeList = batchList.get("codeList");
		HashMap<String, Object> resultMap = null;

		try {
			resultMap = baseSF.batchCodeListProcess(codeList);
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

	@RequestMapping(value = "/batchDetailListProcess", method = RequestMethod.POST)
	public ModelMap batchDetailListProcess(@RequestBody Map<String, ArrayList<LogiCodeDetailTO>> batchList) {

		ArrayList<LogiCodeDetailTO> detailCodeList = batchList.get("detailCodeList");
		;
		HashMap<String, Object> resultMap = null;

		try {

			resultMap = baseSF.batchDetailCodeListProcess(detailCodeList);
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

	@RequestMapping(value = "/changeCodeUseCheckProcess", method = RequestMethod.GET)
	public ModelMap changeCodeUseCheckProcess(HttpServletRequest request, HttpServletResponse response) {

//		String batchList = request.getParameter("batchList");

		try {

//			ArrayList<LogiCodeDetailTO> detailCodeList = null;
//			HashMap<String, Object> resultMap = null;
//
//			detailCodeList = gson.fromJson(batchList, new TypeToken<ArrayList<CodeDetailTO>>() {
//			}.getType());
//
//			resultMap = baseSF.changeCodeUseCheckProcess(detailCodeList);
//
//			modelMap.put("result", resultMap);
//			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

}
