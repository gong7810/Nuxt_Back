package kr.co.seoulit.erp.logistic.base.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.logistic.base.to.LogiCodeDetailTO;
import kr.co.seoulit.erp.logistic.base.to.LogiCodeTO;

public interface LogiCodeApplicationService {

	public ArrayList<LogiCodeDetailTO> getDetailCodeList(String divisionCode);

	public ArrayList<LogiCodeTO> getCodeList();

	public HashMap<String, Object> batchCodeListProcess(ArrayList<LogiCodeTO> codeList);

	public HashMap<String, Object> batchDetailCodeListProcess(ArrayList<LogiCodeDetailTO> detailCodeList);

	public HashMap<String, Object> changeCodeUseCheckProcess(ArrayList<LogiCodeDetailTO> detailCodeList);

	public String createDivisionCodeNo(String codeType);

}
