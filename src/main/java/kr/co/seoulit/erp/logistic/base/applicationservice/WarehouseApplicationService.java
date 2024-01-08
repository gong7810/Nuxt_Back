package kr.co.seoulit.erp.logistic.base.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.logistic.base.to.WarehouseTO;

public interface WarehouseApplicationService {

	public ArrayList<WarehouseTO> getWarehouseInfoList();

	public HashMap<String, Object> batchWarehouseInfoProcess(ArrayList<WarehouseTO> warehouseList);

	public void modifyWarehouseInfo(WarehouseTO warehouseTO);

	public String findLastWarehouseCode();
}
