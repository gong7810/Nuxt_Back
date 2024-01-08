package kr.co.seoulit.erp.logistic.production.controller;

import java.util.*;

import kr.co.seoulit.erp.sys.to.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.co.seoulit.erp.logistic.production.servicefacade.ProductionServiceFacade;
import kr.co.seoulit.erp.logistic.production.to.MrpGatheringTO;
import kr.co.seoulit.erp.logistic.production.to.MrpTO;

import static kr.co.seoulit.erp.sys.to.response.Response.success;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/logi/logistics/production/*", produces = "application/json")
public class MrpController {

	@Autowired
	private ProductionServiceFacade productionSF;

	private ModelMap modelMap = new ModelMap();

	private static Gson gson = new GsonBuilder().serializeNulls().create();

	@RequestMapping("/getMrpList")
	public ModelMap getMrpList(@RequestParam(required = false) String mrpGatheringStatusCondition,
			@RequestParam(required = false) String dateSearchCondition,
			@RequestParam(required = false) String mrpStartDate, @RequestParam(required = false) String mrpEndDate,
			@RequestParam(required = false) String mrpGatheringNo) {// (HttpServletRequest request, HttpServletResponse
																	// response) {

		// String mrpGatheringStatusCondition =
		// request.getParameter("mrpGatheringStatusCondition");
		// String dateSearchCondition = request.getParameter("dateSearchCondition");
		// String mrpStartDate = request.getParameter("mrpStartDate");
		// String mrpEndDate = request.getParameter("mrpEndDate");
		// String mrpGatheringNo = request.getParameter("mrpGatheringNo");
		System.out.println("mrpGatheringStatusCondition: " + mrpGatheringStatusCondition);
		System.out.println("dateSearchCondition: " + dateSearchCondition);
		System.out.println("mrpStartDate: " + mrpStartDate);
		System.out.println("mrpEndDate: " + mrpEndDate);
		System.out.println("mrpGatheringNo: " + mrpGatheringNo);

		try {

			ArrayList<MrpTO> mrpList = null;
			mrpList = productionSF.searchMrpList(mrpGatheringStatusCondition);

			if (mrpGatheringStatusCondition != null) {
				// �뿬湲� null�씠�씪�뒗 �뒪�듃留곴컪�씠 �떞寃⑥��솕�쑝�땲 null�� �븘�떂. 媛앹껜媛��엳�뒗�긽�깭.

				mrpList = productionSF.searchMrpList(mrpGatheringStatusCondition);

			} else if (dateSearchCondition != null) {

				mrpList = productionSF.searchMrpList(dateSearchCondition, mrpStartDate, mrpEndDate);

			} else if (mrpGatheringNo != null) {

				mrpList = productionSF.searchMrpListAsMrpGatheringNo(mrpGatheringNo);

			}

			modelMap.put("gridRowJson", mrpList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/openMrp")
	public ModelMap/* HashMap<String, Object> */ openMrp(@RequestParam String mpsNoListStr) {// (HttpServletRequest
																								// request,
																								// HttpServletResponse
																								// response) {
		System.out.println("mpsNoListStr   " + mpsNoListStr);

//		String mpsNoListStr = request.getParameter("mpsNoList");
		/*
		 * ArrayList<String> mpsNoArr = gson.fromJson((mpsNoListStr), new
		 * TypeToken<ArrayList<String>>() { }.getType()); HashMap<String, Object>
		 * resultMap = new HashMap<>();
		 */
		ArrayList<String> mpsNoArr = new ArrayList<>();
		HashMap<String, Object> resultMap = new HashMap<>();
		mpsNoArr.add(0, mpsNoListStr);
		try {
			System.out.println("mpsNoListStr   4" + mpsNoListStr);
			resultMap = productionSF.openMrp(mpsNoArr);
			modelMap.put("gridRowJson", resultMap.get("gridRowJson"));
		} catch (Exception e2) {

			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		// return null;
		return modelMap;

	}

	@RequestMapping(value = "/registerMrp", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Response registerMrp(@RequestBody Map<String, Object> params) {// @RequestBody Map<String, Object> params

		String batchList = params.get("batchList").toString();
		String mrpRegisterDate = params.get("mrpRegisterDate").toString();
		System.out.println("		@ batchList: " + batchList);
		System.out.println("		@ params: " + mrpRegisterDate);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 파라미터Map에서 TO에 들어있지 않는 변수가 있어도
																					// 무시함.
		ArrayList<MrpTO> newMrpList = mapper.convertValue(params.get("batchList"),
				TypeFactory.defaultInstance().constructCollectionType(List.class, MrpTO.class));

		System.out.println("data");
		for (MrpTO to : newMrpList) {
			System.out.println(to.getOrderDate());
			System.out.println(to.getRequiredDate());
		}

		try {
			HashMap<String, Object> resultMap = productionSF.registerMrp(mrpRegisterDate, newMrpList);
			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return success(modelMap);
	}

	@RequestMapping(value = "/getMrpGatheringList")
	@SuppressWarnings("unchecked")
	public ModelMap getMrpGatheringList(@RequestParam String mpsNoList) {
		System.out.println(mpsNoList);
		try {

			ArrayList<MrpGatheringTO> mrpGatheringList = productionSF.getMrpGathering(mpsNoList);
//			System.out.println("		@ mrpGatheringList: "+mrpGatheringList);
			modelMap.put("gridRowJson", mrpGatheringList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping(value = "/registerMrpGathering", method = RequestMethod.PUT)
	public ModelMap registerMrpGathering(@RequestBody(required = false) Map<String, Object> params) {
		String mrpGatheringRegisterDate = params.get("mrpGatheringRegisterDate").toString();
		String batchList = params.get("batchList").toString();
		String mrpNoAndItemCodeList = params.get("mrpNoAndItemCodeList").toString();

		System.out.println(mrpGatheringRegisterDate);
		System.out.println(batchList);
		System.out.println(mrpNoAndItemCodeList);

		/*
		 * String MrpGatheringList = gson.fromJson(batchList, new TypeToken<String>() {
		 * }.getType());
		 */

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 파라미터Map에서 TO에 들어있지 않는 변수가 있어도
																					// 무시함.
		ArrayList<MrpGatheringTO> newMrpGatheringList = mapper.convertValue(params.get("batchList"),
				TypeFactory.defaultInstance().constructCollectionType(List.class, MrpGatheringTO.class));

		HashMap<String, String> mrpNoAndItemCodeMap = gson.fromJson((String) mrpNoAndItemCodeList,
				new TypeToken<HashMap<String, String>>() {
				}.getType());
		try {
			HashMap<String, Object> resultMap = productionSF.registerMrpGathering(mrpGatheringRegisterDate,
					newMrpGatheringList, mrpNoAndItemCodeMap);
			System.out.println("		@ resultMap: " + resultMap);
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

	@RequestMapping(value = "/searchMrpGathering", method = RequestMethod.GET)
	public ModelMap searchMrpGathering(@RequestParam String searchDateCondition, @RequestParam String startDate,
			@RequestParam String endDate) {

		System.out.println("searchDateCondition              " + searchDateCondition);
		System.out.println("startDate              " + startDate);
		System.out.println("endDate              " + endDate);
		try {

			ArrayList<MrpGatheringTO> mrpGatheringList = productionSF.searchMrpGatheringList(searchDateCondition,
					startDate, endDate);

			modelMap.put("gridRowJson", mrpGatheringList);
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
