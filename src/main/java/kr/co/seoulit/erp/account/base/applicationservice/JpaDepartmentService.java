package kr.co.seoulit.erp.account.base.applicationservice;


import kr.co.seoulit.erp.account.base.entity.DepartmentEntity;
import kr.co.seoulit.erp.account.base.entity.DepartmentSelectList;

import java.util.ArrayList;
import java.util.List;

public interface JpaDepartmentService {
    public List<DepartmentSelectList> findDeptList();
//    public List<DepartmentEntity> find();
    //  public List<DepartmentEntity> findDetailDeptList(String workplaceCode);
    public ArrayList<DepartmentEntity> findDetailDeptList(String workplaceCode);
}
