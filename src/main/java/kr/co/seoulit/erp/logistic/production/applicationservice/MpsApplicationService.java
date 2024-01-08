package kr.co.seoulit.erp.logistic.production.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.logistic.production.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.erp.logistic.production.to.MpsTO;
import kr.co.seoulit.erp.logistic.production.to.SalesPlanInMpsAvailableTO;

public interface MpsApplicationService {

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

	// applicationService 내부 메서드
	public String getNewMpsNo(String mpsPlanDate);

	// applicationService 내부 메서드
	public void changeMpsStatusInContractDetail(String mpsStatus, String contractDetailNo);

	// applicationService 내부 메서드
	public void changeMpsStatusInSalesPlan(String mpsStatus, String salesPlanNo);
}
