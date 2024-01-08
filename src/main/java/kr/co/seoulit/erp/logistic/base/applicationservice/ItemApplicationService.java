package kr.co.seoulit.erp.logistic.base.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.co.seoulit.erp.logistic.base.to.ItemInfoTO;
import kr.co.seoulit.erp.logistic.base.to.ItemTO;

public interface ItemApplicationService {

	public ArrayList<ItemInfoTO> getItemInfoList(Map<String, String> params);

	public HashMap<String, Object> batchItemListProcess(ArrayList<ItemTO> itemTOList);

	public int getStandardUnitPrice(String itemCode);

	public int getStandardUnitPriceBox(String itemCode);

	public ArrayList<ItemInfoTO> getOptionItemList();
}
