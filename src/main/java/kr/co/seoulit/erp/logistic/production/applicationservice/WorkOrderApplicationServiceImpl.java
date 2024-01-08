package kr.co.seoulit.erp.logistic.production.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.erp.logistic.production.dao.WorkOrderDAO;
import kr.co.seoulit.erp.logistic.production.to.ProductionPerformanceInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkOrderInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkSiteLogTO;
import kr.co.seoulit.erp.logistic.production.to.WorkSiteSimulationTO;

@SuppressWarnings("unused")
@Component
public class WorkOrderApplicationServiceImpl implements WorkOrderApplicationService {

	@Autowired
	private WorkOrderDAO workOrderDAO;

	@Override
	public HashMap<String, Object> getWorkOrderableMrpList() {
		HashMap<String, Object> result = new HashMap<>();

		HashMap<String, Object> map = new HashMap<>();

		workOrderDAO.getWorkOrderableMrpList(map);

		result.put("gridRowJson", map.get("result"));
		result.put("errorCode", map.get("errorCode"));
		result.put("errorMsg", map.get("errorMsg"));

		System.out.println("데이터확인용");
		System.out.println(result);

		return result;

	}

	@Override
	public HashMap<String, Object> getWorkOrderSimulationList(String mrpNo,String mrpGatheringNo) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("mrpGatheringNo", mrpGatheringNo);
		param.put("mrpNo", mrpNo);

		workOrderDAO.getWorkOrderSimulationList(param);

		System.out.println("모의전개 파람쓰: "+param);

		HashMap<String, Object> map = new HashMap<>();
		map.put("result",param.get("result"));
		map.put("errorCode",param.get("ERROR_CODE"));
		map.put("errorMsg",param.get("ERROR_MSG"));
		System.out.println("모의전개: "+map);

		return param;

	}

	@Override
	public HashMap<String, Object> workOrder(String mrpGatheringNo, String workPlaceCode, String productionProcess) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("mrpGatheringNo", mrpGatheringNo);
		param.put("workPlaceCode", workPlaceCode);
		param.put("productionProcess", productionProcess);

		workOrderDAO.workOrder(param);

		return param;

	}

	@Override
	public ArrayList<WorkOrderInfoTO> getWorkOrderInfoList() {

		return workOrderDAO.selectWorkOrderInfoList();

	}

	@Override
	public HashMap<String, Object> workOrderCompletion(String workOrderNo, String actualCompletionAmount) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("workOrderNo", workOrderNo);
		param.put("actualCompletionAmount", actualCompletionAmount);

		workOrderDAO.workOrderCompletion(param);

		return param;
	}

	@Override
	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList() {

		return workOrderDAO.selectProductionPerformanceInfoList();

	}

	@Override
	public HashMap<String, Object> showWorkSiteSituation(String workSiteCourse, String workOrderNo,
														 String itemClassIfication) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("workSiteCourse", workSiteCourse);
		param.put("workOrderNo", workOrderNo);
		param.put("itemClassIfication", itemClassIfication);

		workOrderDAO.selectWorkSiteSituation(param);

		return param;
	}

	@Override
	public void workCompletion(HashMap<String, ArrayList<WorkSiteSimulationTO>> workOrderInfo) {

		ArrayList<WorkSiteSimulationTO> test = workOrderInfo.get("workOrderInfo");
		StringBuilder sb = new StringBuilder();
//            List<TotalTrialBalanceBean> test=(List<TotalTrialBalanceBean>) param.get("RESULT");
		int aa = 0;
		String workOrderNo = "";
		String parentItemCode = "";
		String itemCode = "";
		StringBuilder test22 = new StringBuilder();
		for (WorkSiteSimulationTO testBean : test) {
			// System.out.println(aa+""+testBean);
			workOrderNo = testBean.getWorkOrderNo();
			parentItemCode = testBean.getParentItemCode();
			sb.append(testBean.getItemCode());
			itemCode += testBean.getItemCode() + ", ";
			aa++;
		}
		System.out.println(workOrderNo);
		System.out.println(parentItemCode);
		System.out.println("리스트테스트" + test22);
		System.out.println("리스트테스트111" + itemCode);
//            String itemCodeList=itemCodeListArr.toString().replace("[", "").replace("]", "");
		HashMap<String, Object> param = new HashMap<>();
		param.put("workOrderNo", workOrderNo);
		param.put("itemCode", parentItemCode);
		param.put("itemCodeList", itemCode);

		workOrderDAO.updateWorkCompletionStatus(param);

	}

	@Override
	public HashMap<String, Object> workSiteLogList(String workSiteLogDate) {

		HashMap<String, Object> result = new HashMap<>();
		List<WorkSiteLogTO> list = workOrderDAO.workSiteLogList(workSiteLogDate);
		result.put("result", list);

		return result;
	}

}