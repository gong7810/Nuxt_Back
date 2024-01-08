package kr.co.seoulit.erp.logistic.production.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.logistic.production.to.ProductionPerformanceInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkOrderInfoTO;
import kr.co.seoulit.erp.logistic.production.to.WorkSiteLogTO;

@Mapper
public interface WorkOrderDAO {

	public HashMap<String, Object> getWorkOrderableMrpList(HashMap<String, Object> result);

	public HashMap<String, Object> getWorkOrderSimulationList(HashMap<String, Object> param);

	public ArrayList<WorkOrderInfoTO> selectWorkOrderInfoList();

	public ArrayList<ProductionPerformanceInfoTO> selectProductionPerformanceInfoList();

	public List<WorkSiteLogTO> workSiteLogList(String workSiteLogDate);

	public HashMap<String, Object> workOrder(HashMap<String, Object> param);

	public HashMap<String, Object> workOrderCompletion(HashMap<String, Object> param);

	public HashMap<String, Object> selectWorkSiteSituation(HashMap<String, Object> param);

	public void updateWorkCompletionStatus(HashMap<String, Object> param);

}
