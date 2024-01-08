package kr.co.seoulit.erp.logistic.base.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.erp.logistic.base.dao.LogiCodeDAO;
import kr.co.seoulit.erp.logistic.base.dao.LogiCodeDetailDAO;
import kr.co.seoulit.erp.logistic.base.to.LogiCodeDetailTO;
import kr.co.seoulit.erp.logistic.base.to.LogiCodeTO;

@Component
public class LogiCodeApplicationServiceImpl implements LogiCodeApplicationService {

	@Autowired
	private LogiCodeDAO codeDAO;
	@Autowired
	private LogiCodeDetailDAO codeDetailDAO;

	@Override
	public ArrayList<LogiCodeTO> getCodeList() {

		ArrayList<LogiCodeTO> codeList = null;

		codeList = codeDAO.selectCodeList();

		for (LogiCodeTO bean : codeList) {

			bean.setCodeDetailTOList(codeDetailDAO.selectDetailCodeList(bean.getDivisionCodeNo()));

		}

		return codeList;
	}

	@Override
	public ArrayList<LogiCodeDetailTO> getDetailCodeList(String divisionCode) {

		return codeDetailDAO.selectDetailCodeList(divisionCode);

	}

	public String createDivisionCodeNo(String codeType) {

		String divisionCodeNo = codeDAO.createDivisionCodeNo(codeType);

		return divisionCodeNo;

	}

	@Override
	public HashMap<String, Object> batchCodeListProcess(ArrayList<LogiCodeTO> codeList) {
		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<LogiCodeTO> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		for (LogiCodeTO bean : codeList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":
				String codeType = bean.getCodeType();
				String divisionCodeNo = createDivisionCodeNo(codeType);
				bean.setDivisionCodeNo(divisionCodeNo);
				bean.setStatus("normal");
				codeDAO.insertCode(bean);
				insertList.add(bean);

				break;

			case "UPDATE":

				codeDAO.updateCode(bean);

				updateList.add(bean.getDivisionCodeNo());

				break;

			case "DELETE":

				codeDAO.deleteCode(bean);

				deleteList.add(bean.getDivisionCodeNo());

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;

	}

	@Override
	public HashMap<String, Object> batchDetailCodeListProcess(ArrayList<LogiCodeDetailTO> detailCodeList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		for (LogiCodeDetailTO bean : detailCodeList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":

				codeDetailDAO.insertDetailCode(bean);

				insertList.add(bean.getDivisionCodeNo() + " / " + bean.getDetailCode());

				break;

			case "UPDATE":

				codeDetailDAO.updateDetailCode(bean);

				updateList.add(bean.getDivisionCodeNo() + " / " + bean.getDetailCode());

				break;

			case "DELETE":

				codeDetailDAO.deleteDetailCode(bean);

				deleteList.add(bean.getDivisionCodeNo() + " / " + bean.getDetailCode());

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;

	}

	@Override
	public HashMap<String, Object> changeCodeUseCheckProcess(ArrayList<LogiCodeDetailTO> detailCodeList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> codeUseList = new ArrayList<>();
		ArrayList<String> codeNotUseList = new ArrayList<>();

		for (LogiCodeDetailTO bean : detailCodeList) {

			String codeUseCheck = ((bean.getCodeUseCheck() == null) ? "" : bean.getCodeUseCheck().toUpperCase());

			switch (codeUseCheck) {

			case "N":

				codeDetailDAO.changeCodeUseCheck(bean.getDivisionCodeNo(), bean.getDetailCode(), "N");

				codeNotUseList.add(bean.getDivisionCodeNo() + " / " + bean.getDetailCode());

				break;

			default:

				codeDetailDAO.changeCodeUseCheck(bean.getDivisionCodeNo(), bean.getDetailCode(), "");

				codeUseList.add(bean.getDivisionCodeNo() + " / " + bean.getDetailCode());

				break;

			}

		}

		resultMap.put("USE", codeUseList);
		resultMap.put("NOT_USE", codeNotUseList);

		return resultMap;
	}

}
