package kr.co.seoulit.erp.logistic.production.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.erp.logistic.production.applicationservice.MpsApplicationService;
import kr.co.seoulit.erp.logistic.production.applicationservice.MrpApplicationService;
import kr.co.seoulit.erp.logistic.production.applicationservice.WorkOrderApplicationService;
import kr.co.seoulit.erp.logistic.production.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.erp.logistic.production.to.MpsTO;
import kr.co.seoulit.erp.logistic.production.to.MrpGatheringTO;
import kr.co.seoulit.erp.logistic.production.to.MrpTO;
import kr.co.seoulit.erp.logistic.production.to.ProductionPerformanceInfoTO;
import kr.co.seoulit.erp.logistic.production.to.SalesPlanInMpsAvailableTO;
import kr.co.seoulit.erp.logistic.production.to.WorkOrderInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkSiteSimulationTO;

@Service
public class ProductionServiceFacadeImpl implements ProductionServiceFacade {

	@Autowired
	private MpsApplicationService mpsAS;
	@Autowired
	private MrpApplicationService mrpAS;
	@Autowired
	private WorkOrderApplicationService workOrderAS;

	@Override
	public ArrayList<MpsTO> getMpsList(String startDate, String endDate, String includeMrpApply) {

		return mpsAS.getMpsList(startDate, endDate, includeMrpApply);
	}

	@Override
	public ArrayList<ContractDetailInMpsAvailableTO> getContractDetailListInMpsAvailable(String searchCondition,
																						 String startDate, String endDate) {

		return mpsAS.getContractDetailListInMpsAvailable(searchCondition, startDate, endDate);
	}

	@Override
	public ArrayList<SalesPlanInMpsAvailableTO> getSalesPlanListInMpsAvailable(String searchCondition, String startDate,
																			   String endDate) {

		return mpsAS.getSalesPlanListInMpsAvailable(searchCondition, startDate, endDate);

	}

	@Override
	public HashMap<String, Object> convertContractDetailToMps(
			ContractDetailInMpsAvailableTO contractDetailInMpsAvailableTO) {

		return mpsAS.convertContractDetailToMps(contractDetailInMpsAvailableTO);

	}

	@Override
	public HashMap<String, Object> convertSalesPlanToMps(
			ArrayList<SalesPlanInMpsAvailableTO> contractDetailInMpsAvailableList) {

		return mpsAS.convertSalesPlanToMps(contractDetailInMpsAvailableList);

	}

	@Override
	public HashMap<String, Object> batchMpsListProcess(ArrayList<MpsTO> mpsTOList) {

		return mpsAS.batchMpsListProcess(mpsTOList);

	}

	@Override
	public ArrayList<MrpTO> searchMrpList(String mrpGatheringStatusCondition) {

		return mrpAS.searchMrpList(mrpGatheringStatusCondition);

	}

	@Override
	public ArrayList<MrpTO> searchMrpList(String dateSearchCondtion, String startDate, String endDate) {

		return mrpAS.searchMrpList(dateSearchCondtion, startDate, endDate);
	}

	@Override
	public ArrayList<MrpTO> searchMrpListAsMrpGatheringNo(String mrpGatheringNo) {

		return mrpAS.searchMrpListAsMrpGatheringNo(mrpGatheringNo);
	}

	@Override
	public ArrayList<MrpGatheringTO> searchMrpGatheringList(String dateSearchCondtion, String startDate,
															String endDate) {

		return mrpAS.searchMrpGatheringList(dateSearchCondtion, startDate, endDate);
	}

	@Override
	public HashMap<String, Object> openMrp(ArrayList<String> mpsNoArr) {

		return mrpAS.openMrp(mpsNoArr);
	}

	@Override
	public HashMap<String, Object> registerMrp(String mrpRegisterDate, ArrayList<MrpTO> newMrpList) {

		return mrpAS.registerMrp(mrpRegisterDate, newMrpList);
	}

	@Override
	public HashMap<String, Object> batchMrpListProcess(ArrayList<MrpTO> mrpTOList) {

		return mrpAS.batchMrpListProcess(mrpTOList);
	}

	@Override
	public ArrayList<MrpGatheringTO> getMrpGathering(String mrpNoArr) {

		return mrpAS.getMrpGathering(mrpNoArr);
	}

	@Override
	public HashMap<String, Object> registerMrpGathering(String mrpGatheringRegisterDate,
														ArrayList<MrpGatheringTO> newMrpGatheringList, HashMap<String, String> mrpNoAndItemCodeMap) {

		return mrpAS.registerMrpGathering(mrpGatheringRegisterDate, newMrpGatheringList, mrpNoAndItemCodeMap);

	}

	@Override
	public HashMap<String, Object> getWorkOrderableMrpList() {

		return workOrderAS.getWorkOrderableMrpList();

	}

	@Override
	public HashMap<String, Object> getWorkOrderSimulationList(String mrpNo,String mrpGatheringNo) {

		return workOrderAS.getWorkOrderSimulationList(mrpNo,mrpGatheringNo);

	}

	@Override
	public HashMap<String, Object> workOrder(String mrpGatheringNo, String workPlaceCode, String productionProcess) {

		return workOrderAS.workOrder(mrpGatheringNo, workPlaceCode, productionProcess);

	}

	@Override
	public ArrayList<WorkOrderInfoTO> getWorkOrderInfoList() {

		return workOrderAS.getWorkOrderInfoList();

	}

	@Override
	public HashMap<String, Object> workOrderCompletion(String workOrderNo, String actualCompletionAmount) {

		return workOrderAS.workOrderCompletion(workOrderNo, actualCompletionAmount);

	}

	@Override
	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList() {

		return workOrderAS.getProductionPerformanceInfoList();

	}

	@Override
	public HashMap<String, Object> showWorkSiteSituation(String workSiteCourse, String workOrderNo,
														 String itemClassIfication) {

		return workOrderAS.showWorkSiteSituation(workSiteCourse, workOrderNo, itemClassIfication);

	}

	@Override
	public void workCompletion(HashMap<String, ArrayList<WorkSiteSimulationTO>> workOrderInfo) {

		workOrderAS.workCompletion(workOrderInfo);

	}

	@Override
	public HashMap<String, Object> workSiteLogList(String workSiteLogDate) {

		return workOrderAS.workSiteLogList(workSiteLogDate);
	}

}
