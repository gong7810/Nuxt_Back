package kr.co.seoulit.erp.logistic.production.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.logistic.production.to.ProductionPerformanceInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkOrderInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkSiteSimulationTO;

public interface WorkOrderApplicationService {

	public HashMap<String, Object> getWorkOrderableMrpList();

	public HashMap<String, Object> getWorkOrderSimulationList(String mrpNo,String mrpGatheringNo);

	public HashMap<String, Object> workOrder(String mrpGatheringNo, String workPlaceCode, String productionProcess);

	public ArrayList<WorkOrderInfoTO> getWorkOrderInfoList();

	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList();

	public HashMap<String, Object> workOrderCompletion(String workOrderNo, String actualCompletionAmount);

	public HashMap<String, Object> showWorkSiteSituation(String workSiteCourse, String workOrderNo,
														 String itemClassIfication);

	public void workCompletion(HashMap<String, ArrayList<WorkSiteSimulationTO>> workOrderInfo);

	public HashMap<String, Object> workSiteLogList(String workSiteLogDate);

}