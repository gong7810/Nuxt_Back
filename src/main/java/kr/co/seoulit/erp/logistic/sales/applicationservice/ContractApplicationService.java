package kr.co.seoulit.erp.logistic.sales.applicationservice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.logistic.sales.to.*;

public interface ContractApplicationService {

	public ArrayList<ContractInfoTO> getContractList(String startDate, String endDate);

	public ArrayList<ContractInfoTO> getContractListByCustomer(String customerCode);

	public ArrayList<ContractDetailTO> getContractDetailList(String estimateNo);

	public ArrayList<EstimateTO> getEstimateListInContractAvailable(String startDate, String endDate);

	public String getNewContractNo(String contractDate);

	public HashMap<String, Object> addNewContract(String contractDate, String personCodeInCharge,
												  ContractTO workingContractTO, ArrayList<EstimateDetailTO> estimateDetailTO);

	public HashMap<String, Object> batchContractDetailListProcess(ArrayList<ContractDetailTO> contractDetailTOList);

	public void changeContractStatusInEstimate(String estimateNo, String contractStatus);

	public ArrayList<ContractInfoTO> getDeliverableContractList(String searchCondition, String[] paramArray);

}
