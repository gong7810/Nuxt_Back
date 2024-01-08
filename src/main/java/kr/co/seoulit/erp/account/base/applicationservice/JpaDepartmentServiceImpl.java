package kr.co.seoulit.erp.account.base.applicationservice;


import kr.co.seoulit.erp.account.base.entity.DepartmentEntity;
import kr.co.seoulit.erp.account.base.entity.DepartmentSelectList;
import kr.co.seoulit.erp.account.base.repository.JpaDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaDepartmentServiceImpl implements JpaDepartmentService {
    @Autowired
    private JpaDepartmentRepository jpaDepartmentRepository;

    @Override
    public List<DepartmentSelectList> findDeptList() {
        // TODO Auto-generated method stub

        //  ArrayList<DepartmentBean> deptList=usrePermissionGroupDAO.selectDept();
        List<DepartmentSelectList> deptList = jpaDepartmentRepository.findDistinctWorkplaceCode();
        System.out.println("확인:" + deptList);
        return deptList;
    }

//    @Override
//    public List<DepartmentEntity> find(){
//        List<DepartmentEntity> deptList = jpaDepartmentRepository.findAllBy();
//        return deptList;
//
//    }

    @Override
    public ArrayList<DepartmentEntity> findDetailDeptList(String workplaceCode) {
        // TODO Auto-generated method stub
        ArrayList<DepartmentEntity> detailDeptList = jpaDepartmentRepository.findByWorkplaceCode(workplaceCode);
        System.out.println("부서 디테일부서리스트 확인:: " + detailDeptList);
        return detailDeptList;
    }


}
