package kr.co.seoulit.erp.logistic.base.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.erp.logistic.base.dao.ItemDAO;
import kr.co.seoulit.erp.logistic.base.dao.LogiCodeDetailDAO;
import kr.co.seoulit.erp.logistic.base.to.ItemInfoTO;
import kr.co.seoulit.erp.logistic.base.to.ItemTO;
import kr.co.seoulit.erp.logistic.base.to.LogiCodeDetailTO;
import kr.co.seoulit.erp.logistic.purchase.dao.BomDAO;
import kr.co.seoulit.erp.logistic.purchase.to.BomTO;

@Component
public class ItemApplicationServiceImpl implements ItemApplicationService {

	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private LogiCodeDetailDAO codeDetailDAO;
	@Autowired
	private BomDAO bomDAO;

	public ArrayList<ItemInfoTO> getItemInfoList(Map<String, String> params) {

		ArrayList<ItemInfoTO> itemInfoList = null;

		switch (params.get("searchCondition")) {

		case "ALL":

			itemInfoList = itemDAO.selectAllItemList();

			break;

		case "ITEM_CLASSIFICATION":
			System.out.println("AS : " + params.get("searchCondition"));
			System.out.println("AS : " + params.get("itemClassification"));
			itemInfoList = itemDAO.selectItemList(params);

			break;

		case "ITEM_GROUP_CODE":
			System.out.println("AS : " + params.get("searchCondition"));
			itemInfoList = itemDAO.selectItemList(params);

			break;

		case "STANDARD_UNIT_PRICE":

			itemInfoList = itemDAO.selectItemList(params);

			break;

		}

		return itemInfoList;
	}

	public HashMap<String, Object> batchItemListProcess(ArrayList<ItemTO> itemTOList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		LogiCodeDetailTO detailCodeTO = new LogiCodeDetailTO();
		BomTO bomTO = new BomTO();

		for (ItemTO TO : itemTOList) {

			String status = TO.getStatus();

			switch (status) {

			case "INSERT":

				itemDAO.insertItem(TO);
				insertList.add(TO.getItemCode());

				// CODE_DETAIL 테이블에 Insert
				detailCodeTO.setDivisionCodeNo(TO.getItemClassification());
				detailCodeTO.setDetailCode(TO.getItemCode());
				detailCodeTO.setDetailCodeName(TO.getItemName());
				detailCodeTO.setDescription(TO.getDescription());

				codeDetailDAO.insertDetailCode(detailCodeTO);

				// 새로운 품목이 완제품 (ItemClassification : "IT-CI") , 반제품 (ItemClassification :
				// "IT-SI") 일 경우 BOM 테이블에 Insert
				if (TO.getItemClassification().equals("IT-CI") || TO.getItemClassification().equals("IT-SI")) {

					bomTO.setNo(1);
					bomTO.setParentItemCode("NULL");
					bomTO.setItemCode(TO.getItemCode());
					bomTO.setNetAmount(1);

					bomDAO.insertBom(bomTO);
				}

				break;

			case "UPDATE":

				itemDAO.updateItem(TO);

				updateList.add(TO.getItemCode());

				// CODE_DETAIL 테이블에 Update
				detailCodeTO.setDivisionCodeNo(TO.getItemClassification());
				detailCodeTO.setDetailCode(TO.getItemCode());
				detailCodeTO.setDetailCodeName(TO.getItemName());
				detailCodeTO.setDescription(TO.getDescription());

				codeDetailDAO.updateDetailCode(detailCodeTO);

				break;

			case "DELETE":

				itemDAO.deleteItem(TO);

				deleteList.add(TO.getItemCode());

				// CODE_DETAIL 테이블에 Delete
				detailCodeTO.setDivisionCodeNo(TO.getItemClassification());
				detailCodeTO.setDetailCode(TO.getItemCode());
				detailCodeTO.setDetailCodeName(TO.getItemName());
				detailCodeTO.setDescription(TO.getDescription());

				codeDetailDAO.deleteDetailCode(detailCodeTO);

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;

	}

	@Override
	public int getStandardUnitPrice(String itemCode) {

		return itemDAO.getStandardUnitPrice(itemCode);
	}

	@Override
	public int getStandardUnitPriceBox(String itemCode) {

		return itemDAO.getStandardUnitPriceBox(itemCode);
	}

	@Override
	public ArrayList<ItemInfoTO> getOptionItemList() {
		return itemDAO.selectOptionItemList();
	}

}
