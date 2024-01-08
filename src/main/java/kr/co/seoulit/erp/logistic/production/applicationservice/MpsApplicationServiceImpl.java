package kr.co.seoulit.erp.logistic.production.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.erp.logistic.production.dao.MpsDAO;
import kr.co.seoulit.erp.logistic.production.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.erp.logistic.production.to.MpsTO;
import kr.co.seoulit.erp.logistic.production.to.SalesPlanInMpsAvailableTO;
import kr.co.seoulit.erp.logistic.sales.dao.ContractDetailDAO;
import kr.co.seoulit.erp.logistic.sales.dao.SalesPlanDAO;

@Component
public class MpsApplicationServiceImpl implements MpsApplicationService {

	@Autowired
	private MpsDAO mpsDAO;
	@Autowired
	private ContractDetailDAO contractDetailDAO;
	@Autowired
	private SalesPlanDAO salesPlanDAO;

	public ArrayList<MpsTO> getMpsList(String startDate, String endDate, String includeMrpApply) {

		HashMap<String, String> param = new HashMap<>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("includeMrpApply", includeMrpApply);

		return mpsDAO.selectMpsList(param);
	}

	public ArrayList<ContractDetailInMpsAvailableTO> getContractDetailListInMpsAvailable(String searchCondition,
			String startDate, String endDate) {

		HashMap<String, String> param = new HashMap<>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("searchCondition", searchCondition);

		return contractDetailDAO.selectContractDetailListInMpsAvailable(param);
	}

	public ArrayList<SalesPlanInMpsAvailableTO> getSalesPlanListInMpsAvailable(String searchCondition, String startDate,
			String endDate) {

		HashMap<String, String> param = new HashMap<>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("searchCondition", searchCondition);

		return salesPlanDAO.selectSalesPlanListInMpsAvailable(param);
	}

	public String getNewMpsNo(String mpsPlanDate) {

		StringBuffer newEstimateNo = null;

		List<MpsTO> mpsTOlist = mpsDAO.selectMpsCount(mpsPlanDate);
		TreeSet<Integer> intSet = new TreeSet<>();
		int i;
		for (MpsTO bean : mpsTOlist) {
			String mpsNo = bean.getMpsNo();

			// MPS 일련번호에서 마지막 2자리만 가져오기
			int no = Integer.parseInt(mpsNo.substring(mpsNo.length() - 2, mpsNo.length()));

			intSet.add(no);
		}

		if (intSet.isEmpty()) {
			i = 1;
		} else {
			i = intSet.pollLast() + 1; // 가장 높은 번호 + 1
		}

		newEstimateNo = new StringBuffer();
		newEstimateNo.append("PS");
		newEstimateNo.append(mpsPlanDate.replace("-", ""));
		newEstimateNo.append(String.format("%02d", i));

		return newEstimateNo.toString();
	}

	public HashMap<String, Object> convertContractDetailToMps(
			ContractDetailInMpsAvailableTO contractDetailInMpsAvailableTO) {

		HashMap<String, Object> resultMap = null;

		ArrayList<MpsTO> mpsTOList = new ArrayList<>();

		MpsTO newMpsBean = null;

		// MPS 에 등록할 수주상세 Bean 의 정보를 새로운 MPS Bean 에 세팅, status : "INSERT"

		System.out.println("convertContractDetailToMps ApplicationServiceImpl접근----------------------------"
				+ contractDetailInMpsAvailableTO.getContractDetailNo());
		newMpsBean = new MpsTO();

		newMpsBean.setStatus("INSERT");

		newMpsBean.setMpsPlanClassification(contractDetailInMpsAvailableTO.getPlanClassification());
		newMpsBean.setContractDetailNo(contractDetailInMpsAvailableTO.getContractDetailNo());
		newMpsBean.setItemCode(contractDetailInMpsAvailableTO.getItemCode());
		newMpsBean.setItemName(contractDetailInMpsAvailableTO.getItemName());
		newMpsBean.setUnitOfMps(contractDetailInMpsAvailableTO.getUnitOfContract());
		newMpsBean.setMpsPlanDate(contractDetailInMpsAvailableTO.getMpsPlanDate());
		newMpsBean.setMpsPlanAmount(contractDetailInMpsAvailableTO.getProductionRequirement());
		newMpsBean.setDueDateOfMps(contractDetailInMpsAvailableTO.getDueDateOfContract());
		newMpsBean.setScheduledEndDate(contractDetailInMpsAvailableTO.getScheduledEndDate());
		newMpsBean.setDescription(contractDetailInMpsAvailableTO.getDescription());

		mpsTOList.add(newMpsBean);

		resultMap = batchMpsListProcess(mpsTOList); // batchMpsListProcess 메소드 호출

		return resultMap;

	}

	public HashMap<String, Object> convertSalesPlanToMps(
			ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList) {

		HashMap<String, Object> resultMap = null;

		ArrayList<MpsTO> mpsTOList = new ArrayList<>();

		MpsTO newMpsBean = null;

		// MPS 에 등록할 판매계획 TO 의 정보를 새로운 MPS TO 에 세팅, status : "INSERT"
		for (SalesPlanInMpsAvailableTO bean : salesPlanInMpsAvailableList) {

			newMpsBean = new MpsTO();

			newMpsBean.setStatus("INSERT");

			newMpsBean.setMpsPlanClassification(bean.getPlanClassification());
			newMpsBean.setSalesPlanNo(bean.getSalesPlanNo());
			newMpsBean.setItemCode(bean.getItemCode());
			newMpsBean.setItemName(bean.getItemName());
			newMpsBean.setUnitOfMps(bean.getUnitOfSales());
			newMpsBean.setMpsPlanDate(bean.getMpsPlanDate());
			newMpsBean.setMpsPlanAmount(bean.getSalesAmount());
			newMpsBean.setDueDateOfMps(bean.getDueDateOfSales());
			newMpsBean.setScheduledEndDate(bean.getScheduledEndDate());
			newMpsBean.setDescription(bean.getDescription());

			mpsTOList.add(newMpsBean);

		}

		resultMap = batchMpsListProcess(mpsTOList);

		return resultMap;
	}

	public HashMap<String, Object> batchMpsListProcess(ArrayList<MpsTO> mpsTOList) {

		HashMap<String, Object> resultMap = null;

		resultMap = new HashMap<>();
		System.out.println("application다음으로 옮겨온곳 = " + mpsTOList);
		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		for (MpsTO bean : mpsTOList) {

			String status = bean.getStatus();
			System.out.println("bean에서 뽑아낸 status의 값은 ::::::::::" + status);

			switch (status) {

			case "INSERT":
				// 새로운 판매계획일련번호 생성
				String newMpsNo = getNewMpsNo(bean.getMpsPlanDate());
				System.out.println("newMpsNo = " + newMpsNo);

				// MPS TO 에 새로운 판매계획일련번호 세팅
				bean.setMpsNo(newMpsNo);

				// MPS TO Insert
				mpsDAO.insertMps(bean);

				// 생성된 새로운 MPS 번호를 ArrayList 에 추가
				insertList.add(newMpsNo);

				// MPS TO 의 수주상세번호가 존재하면, 수주상세 테이블에서 해당 번호의 MPS 적용상태를 'Y' 로 변경
				if (bean.getContractDetailNo() != null) {

					changeMpsStatusInContractDetail(bean.getContractDetailNo(), "Y");

					// MPS TO 의 판매계획번호가 존재하면, 판매계획 테이블에서 해당 번호의 MPS 적용상태를 'Y' 로 변경
				} else if (bean.getSalesPlanNo() != null) {

					changeMpsStatusInSalesPlan(bean.getSalesPlanNo(), "Y");

				}

				break;

			case "UPDATE":

				mpsDAO.updateMps(bean);

				updateList.add(bean.getMpsNo());

				break;

			case "DELETE":

				mpsDAO.deleteMps(bean);

				deleteList.add(bean.getMpsNo());

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;
	}

	public void changeMpsStatusInContractDetail(String contractDetailNo, String mpsStatus) {

		HashMap<String, String> param = new HashMap<>();
		param.put("contractDetailNo", contractDetailNo);
		param.put("mpsStatus", mpsStatus);

		contractDetailDAO.changeMpsStatusOfContractDetail(param);

	}

	public void changeMpsStatusInSalesPlan(String salesPlanNo, String mpsStatus) {

		HashMap<String, String> param = new HashMap<>();
		param.put("salesPlanNo", salesPlanNo);
		param.put("mpsStatus", mpsStatus);

		salesPlanDAO.changeMpsStatusOfSalesPlan(param);

	}

}
