package kr.co.seoulit.erp.logistic.base.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.erp.logistic.base.dao.WarehouseDAO;
import kr.co.seoulit.erp.logistic.base.to.WarehouseTO;

@Component
public class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

	@Autowired
	private WarehouseDAO warehouseDAO;

	@Override
	public ArrayList<WarehouseTO> getWarehouseInfoList() {

		return warehouseDAO.selectWarehouseList();
	}

	public String createWarehouseCode() {
		int temporaryCode = warehouseDAO.createWarehouseCode();
		String warehouseCode = "WHS-" + String.format("%02d", temporaryCode + 1);

		return warehouseCode;
	}

	@Override
	public HashMap<String, Object> batchWarehouseInfoProcess(ArrayList<WarehouseTO> warehouseList) {
		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<WarehouseTO> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		for (WarehouseTO bean : warehouseList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":

				String warehouseCode = createWarehouseCode();
				System.out.println("코드생성??" + warehouseCode);
				bean.setWarehouseCode(warehouseCode);
				bean.setStatus("normal");
				warehouseDAO.insertCode(bean);
				insertList.add(bean);

				break;

			case "UPDATE":

				warehouseDAO.updateCode(bean);

				// updateList.add(bean.getDivisionCodeNo());

				break;

			case "DELETE":

				warehouseDAO.deleteCode(bean);

				// deleteList.add(bean.getDivisionCodeNo());

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;
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

}
