package kr.co.seoulit.erp.hr.company.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.hr.company.to.DepartmentTO;

public interface DepartmentApplicationService {

	// 부서 전체조회
	public ArrayList<DepartmentTO> getDepartmentList(String searchCondition, String companyCode,
			String workplaceCode);
	
	public String getNewDepartmentCode(String companyCode);
	
	public HashMap<String, Object> batchDepartmentListProcess(ArrayList<DepartmentTO> departmentList);

}
