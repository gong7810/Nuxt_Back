package kr.co.seoulit.erp.logistic.sales.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.erp.logistic.sales.to.ContractDetailTO;
import kr.co.seoulit.erp.logistic.sales.to.ContractInfoTO;
import kr.co.seoulit.erp.logistic.sales.to.ContractTO;
import kr.co.seoulit.erp.logistic.sales.to.DeliveryInfoTO;
import kr.co.seoulit.erp.logistic.sales.to.EstimateDetailTO;
import kr.co.seoulit.erp.logistic.sales.to.EstimateTO;
import kr.co.seoulit.erp.logistic.sales.to.SalesPlanTO;
import kr.co.seoulit.erp.logistic.sales.to.logisticExelTO;

public interface SalesServiceFacade {

	// EstimateApplicationServiceImpl
	public ArrayList<EstimateTO> getEstimateList(String dateSearchCondition, String startDate, String endDate);

	public ArrayList<EstimateDetailTO> getEstimateDetailList(String estimateNo);

	public ArrayList<logisticExelTO> getLogisticExel(String estimateNo);

	public HashMap<String, Object> addNewEstimate(String estimateDate, EstimateTO newEstimateTO);

	public HashMap<String, Object> batchEstimateDetailListProcess(ArrayList<EstimateDetailTO> estimateDetailTOList);

	// ContractApplicationServiceImpl
	public ArrayList<ContractInfoTO> getContractList(String startDate, String endDate);

	public ArrayList<ContractInfoTO> getContractListByCustomer(String customerCode);

	public ArrayList<ContractInfoTO> getDeliverableContractList(String searchCondition, String[] paramArray);

	public ArrayList<ContractDetailTO> getContractDetailList(String estimateNo);

	public ArrayList<EstimateTO> getEstimateListInContractAvailable(String startDate, String endDate);

//	description:	파라미터 타입 & 이름 변경

	public HashMap<String, Object> addNewContract(String contractDate, String personCodeInCharge,
			ContractTO workingContractTO, ArrayList<EstimateDetailTO> estimateDetailTO);

	public HashMap<String, Object> batchContractDetailListProcess(ArrayList<ContractDetailTO> contractDetailTOList);

	public void changeContractStatusInEstimate(String estimateNo, String contractStatus);

	// SalesPlanApplicationServiceImpl
	public ArrayList<SalesPlanTO> getSalesPlanList(String dateSearchCondition, String startDate, String endDate);

	public void batchSalesPlanListProcess(SalesPlanTO salesPlanTOList);

	public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryInfoTO> deliveryTOList);

	public HashMap<String, Object> deliver(String contractDetailNo);

	public ArrayList<DeliveryInfoTO> getDeliveryInfoList();

	public Map<String, ArrayList<DeliveryInfoTO>> deliverUpdate(Map<String, ArrayList<DeliveryInfoTO>> deliverUpdate);
}
