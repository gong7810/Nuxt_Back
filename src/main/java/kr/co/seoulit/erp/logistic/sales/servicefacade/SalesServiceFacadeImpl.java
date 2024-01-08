package kr.co.seoulit.erp.logistic.sales.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.erp.logistic.sales.applicationservice.ContractApplicationService;
import kr.co.seoulit.erp.logistic.sales.applicationservice.DeliveryApplicationService;
import kr.co.seoulit.erp.logistic.sales.applicationservice.EstimateApplicationService;
import kr.co.seoulit.erp.logistic.sales.applicationservice.SalesPlanApplicationService;
import kr.co.seoulit.erp.logistic.sales.to.ContractDetailTO;
import kr.co.seoulit.erp.logistic.sales.to.ContractInfoTO;
import kr.co.seoulit.erp.logistic.sales.to.ContractTO;
import kr.co.seoulit.erp.logistic.sales.to.DeliveryInfoTO;
import kr.co.seoulit.erp.logistic.sales.to.EstimateDetailTO;
import kr.co.seoulit.erp.logistic.sales.to.EstimateTO;
import kr.co.seoulit.erp.logistic.sales.to.SalesPlanTO;
import kr.co.seoulit.erp.logistic.sales.to.logisticExelTO;

@Service
public class SalesServiceFacadeImpl implements SalesServiceFacade {

	@Autowired
	private EstimateApplicationService estimateAS;
	@Autowired
	private ContractApplicationService contractAS;
	@Autowired
	private SalesPlanApplicationService salesPlanAS;
	@Autowired
	private DeliveryApplicationService deliveryAS;

	@Override
	public ArrayList<EstimateTO> getEstimateList(String dateSearchCondition, String startDate, String endDate) {

		return estimateAS.getEstimateList(dateSearchCondition, startDate, endDate);
	}

	@Override
	public ArrayList<EstimateDetailTO> getEstimateDetailList(String estimateNo) {

		return estimateAS.getEstimateDetailList(estimateNo);
	}

	@Override
	public ArrayList<logisticExelTO> getLogisticExel(String estimateNo) {

		return estimateAS.getLogisticExel(estimateNo);
	}

	@Override
	public HashMap<String, Object> addNewEstimate(String estimateDate, EstimateTO newEstimateTO) {

		return estimateAS.addNewEstimate(estimateDate, newEstimateTO);
	}

	@Override
	public HashMap<String, Object> batchEstimateDetailListProcess(ArrayList<EstimateDetailTO> estimateDetailTOList) {

		return estimateAS.batchEstimateDetailListProcess(estimateDetailTOList);
	}

	@Override
	public ArrayList<ContractInfoTO> getContractList(String startDate, String endDate) {

		return contractAS.getContractList(startDate, endDate);
	}

	@Override
	public ArrayList<ContractInfoTO> getContractListByCustomer(String customerCode) {
		return contractAS.getContractListByCustomer(customerCode);
	}

	@Override
	public ArrayList<ContractInfoTO> getDeliverableContractList(String searchCondition, String[] paramArray) {

		return contractAS.getDeliverableContractList(searchCondition, paramArray);
	}

	@Override
	public ArrayList<ContractDetailTO> getContractDetailList(String estimateNo) {

		return contractAS.getContractDetailList(estimateNo);
	}

	@Override
	public ArrayList<EstimateTO> getEstimateListInContractAvailable(String startDate, String endDate) {

		return contractAS.getEstimateListInContractAvailable(startDate, endDate);
	}

//************************* 2020.09.04 63기 양지훈 수정 시작 *************************
//	description:	파라미터 타입 & 이름 변경

	@Override
	public HashMap<String, Object> addNewContract(String contractDate, String personCodeInCharge,
			ContractTO workingContractTO, ArrayList<EstimateDetailTO> estimateDetailArray) {

		return contractAS.addNewContract(contractDate, personCodeInCharge, workingContractTO, estimateDetailArray);
	}

//************************* 2020.09.04 63기 양지훈 수정 종료 *************************

	@Override
	public HashMap<String, Object> batchContractDetailListProcess(ArrayList<ContractDetailTO> contractDetailTOList) {

		return contractAS.batchContractDetailListProcess(contractDetailTOList);

	}

	@Override
	public void changeContractStatusInEstimate(String estimateNo, String contractStatus) {

		contractAS.changeContractStatusInEstimate(estimateNo, contractStatus);
	}

	@Override
	public ArrayList<SalesPlanTO> getSalesPlanList(String dateSearchCondition, String startDate, String endDate) {

		return salesPlanAS.getSalesPlanList(dateSearchCondition, startDate, endDate);
	}

	@Override
	public void batchSalesPlanListProcess(SalesPlanTO salesPlanTOList) {

		salesPlanAS.batchSalesPlanListProcess(salesPlanTOList);
	}

	@Override
	public ArrayList<DeliveryInfoTO> getDeliveryInfoList() {

		return deliveryAS.getDeliveryInfoList();
	}

	@Override
	public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryInfoTO> deliveryTOList) {

		return deliveryAS.batchDeliveryListProcess(deliveryTOList);
	}

	@Override
	public HashMap<String, Object> deliver(String contractDetailNo) {

		return deliveryAS.deliver(contractDetailNo);
	}

	// 미노
	@Override
	public Map<String, ArrayList<DeliveryInfoTO>> deliverUpdate(Map<String, ArrayList<DeliveryInfoTO>> deliverUpdate) {

		return deliveryAS.deliverUpdate(deliverUpdate);
	}

}
