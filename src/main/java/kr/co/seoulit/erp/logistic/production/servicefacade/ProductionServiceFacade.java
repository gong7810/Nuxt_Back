package kr.co.seoulit.erp.logistic.production.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.logistic.production.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.erp.logistic.production.to.MpsTO;
import kr.co.seoulit.erp.logistic.production.to.MrpGatheringTO;
import kr.co.seoulit.erp.logistic.production.to.MrpTO;
import kr.co.seoulit.erp.logistic.production.to.ProductionPerformanceInfoTO;
import kr.co.seoulit.erp.logistic.production.to.SalesPlanInMpsAvailableTO;
import kr.co.seoulit.erp.logistic.production.to.WorkOrderInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkSiteSimulationTO;

public interface ProductionServiceFacade {

	public ArrayList<MpsTO> getMpsList(String startDate, String endDate, String includeMrpApply);

	public ArrayList<ContractDetailInMpsAvailableTO> getContractDetailListInMpsAvailable(String searchCondition,
																						 String startDate, String endDate);

	public ArrayList<SalesPlanInMpsAvailableTO> getSalesPlanListInMpsAvailable(String searchCondition, String startDate,
																			   String endDate);

	public HashMap<String, Object> convertContractDetailToMps(
			ContractDetailInMpsAvailableTO contractDetailInMpsAvailableTO);

	public HashMap<String, Object> convertSalesPlanToMps(
			ArrayList<SalesPlanInMpsAvailableTO> contractDetailInMpsAvailableList);

	public HashMap<String, Object> batchMpsListProcess(ArrayList<MpsTO> mpsTOList);

	public ArrayList<MrpTO> searchMrpList(String mrpGatheringStatusCondition);

	public ArrayList<MrpTO> searchMrpList(String dateSearchCondtion, String startDate, String endDate);

	public ArrayList<MrpTO> searchMrpListAsMrpGatheringNo(String mrpGatheringNo);

	public ArrayList<MrpGatheringTO> searchMrpGatheringList(String dateSearchCondtion, String startDate,
															String endDate);

	public HashMap<String, Object> openMrp(ArrayList<String> mpsNoArr);

	public HashMap<String, Object> registerMrp(String mrpRegisterDate, ArrayList<MrpTO> newMrpList);

	public HashMap<String, Object> batchMrpListProcess(ArrayList<MrpTO> mrpTOList);

	public ArrayList<MrpGatheringTO> getMrpGathering(String mrpNoArr);

	public HashMap<String, Object> registerMrpGathering(String mrpGatheringRegisterDate,
														ArrayList<MrpGatheringTO> newMrpGatheringList, HashMap<String, String> mrpNoAndItemCodeMap);

	public HashMap<String, Object> getWorkOrderableMrpList();

	public HashMap<String, Object> getWorkOrderSimulationList(String mrpNo,String mrpGatheringNo);

	public HashMap<String, Object> workOrder(String mrpGatheringNo, String workPlaceCode, String productionProcess);

	public ArrayList<WorkOrderInfoTO> getWorkOrderInfoList();

	public HashMap<String, Object> workOrderCompletion(String workOrderNo, String actualCompletionAmount);

	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList();

	public HashMap<String, Object> showWorkSiteSituation(String workSiteCourse, String workOrderNo,
														 String itemClassIfication);

	public void workCompletion(HashMap<String, ArrayList<WorkSiteSimulationTO>> workOrderInfo);

	public HashMap<String, Object> workSiteLogList(String workSiteLogDate);

}