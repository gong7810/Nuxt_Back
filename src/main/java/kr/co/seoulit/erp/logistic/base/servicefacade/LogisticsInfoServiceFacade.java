package kr.co.seoulit.erp.logistic.base.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.co.seoulit.erp.logistic.base.to.ItemInfoTO;
import kr.co.seoulit.erp.logistic.base.to.ItemTO;
import kr.co.seoulit.erp.logistic.base.to.WarehouseTO;

public interface LogisticsInfoServiceFacade {

	public ArrayList<ItemInfoTO> getItemInfoList(Map<String, String> params);

	public HashMap<String, Object> batchItemListProcess(ArrayList<ItemTO> itemTOList);

	public ArrayList<WarehouseTO> getWarehouseInfoList();

	public void modifyWarehouseInfo(WarehouseTO warehouseTO);

	public String findLastWarehouseCode();

	public int getStandardUnitPrice(String itemCode);

	public int getStandardUnitPriceBox(String itemCode);

	public ArrayList<ItemInfoTO> getOptionItemList();

	public HashMap<String, Object> batchWarehouseInfoProcess(ArrayList<WarehouseTO> warehouseList);

}
