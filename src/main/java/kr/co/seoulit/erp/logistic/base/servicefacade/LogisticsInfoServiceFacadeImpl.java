package kr.co.seoulit.erp.logistic.base.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.erp.logistic.base.applicationservice.ItemApplicationService;
import kr.co.seoulit.erp.logistic.base.applicationservice.WarehouseApplicationService;
import kr.co.seoulit.erp.logistic.base.to.ItemInfoTO;
import kr.co.seoulit.erp.logistic.base.to.ItemTO;
import kr.co.seoulit.erp.logistic.base.to.WarehouseTO;

@Service
public class LogisticsInfoServiceFacadeImpl implements LogisticsInfoServiceFacade {

	@Autowired
	private ItemApplicationService itemAS;
	@Autowired
	private WarehouseApplicationService warehouseAS;

	@Override
	public ArrayList<ItemInfoTO> getItemInfoList(Map<String, String> params) {

		return itemAS.getItemInfoList(params);
	}

	@Override
	public HashMap<String, Object> batchItemListProcess(ArrayList<ItemTO> itemTOList) {

		return itemAS.batchItemListProcess(itemTOList);
	}

	@Override
	public ArrayList<WarehouseTO> getWarehouseInfoList() {

		return warehouseAS.getWarehouseInfoList();
	}

	@Override
	public void modifyWarehouseInfo(WarehouseTO warehouseTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public String findLastWarehouseCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStandardUnitPrice(String itemCode) {

		return itemAS.getStandardUnitPrice(itemCode);

	}

	@Override
	public int getStandardUnitPriceBox(String itemCode) {

		return itemAS.getStandardUnitPriceBox(itemCode);

	}

	@Override
	public ArrayList<ItemInfoTO> getOptionItemList() {
		return itemAS.getOptionItemList();
	}

	@Override
	public HashMap<String, Object> batchWarehouseInfoProcess(ArrayList<WarehouseTO> warehouseList) {
		// TODO Auto-generated method stub
		return warehouseAS.batchWarehouseInfoProcess(warehouseList);
	}

}
