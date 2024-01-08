package kr.co.seoulit.erp.logistic.sales.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.logistic.production.to.ContractDetailInMpsAvailableTO;

import kr.co.seoulit.erp.logistic.sales.to.ContractDetailTO;

@Mapper
public interface ContractDetailDAO {

	public ArrayList<ContractDetailTO> selectContractDetailList(String contractNo);

	public ArrayList<ContractDetailTO> selectDeliverableContractDetailList(String contractNo);

	public int selectContractDetailCount(String contractNo);
	/*
	 * public void insertContractDetail(ContractDetailTO TO);
	 * 
	 * public void updateContractDetail(ContractDetailTO TO);
	 */

	public void deleteContractDetail(ContractDetailTO TO);

	void insertContractDetail(ContractDetailTO bean);

	void updateContractDetail(ContractDetailTO bean);

	public ArrayList<ContractDetailInMpsAvailableTO> selectContractDetailListInMpsAvailable(
			HashMap<String, String> param);

	public void changeMpsStatusOfContractDetail(HashMap<String, String> param);

	public HashMap<String, Object> procedureInsertContractDetail(HashMap<String, Object> param);

}
