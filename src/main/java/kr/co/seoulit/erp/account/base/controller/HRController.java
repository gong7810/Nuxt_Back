package kr.co.seoulit.erp.account.base.controller;


import kr.co.seoulit.erp.account.base.applicationservice.JpaDepartmentService;
import kr.co.seoulit.erp.account.base.entity.DepartmentEntity;
import kr.co.seoulit.erp.account.base.entity.DepartmentSelectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/operate/*")
public class HRController {


    @Autowired
    private JpaDepartmentService jpaDepartmentService;

    // 복합키사용해야함 key 2개사용
    // ========== JPA 구현 ==========
    @GetMapping("/deptList")
    public HashMap<String, Object> findDeptList(){
        HashMap<String, Object> map= new HashMap<>();
        List<DepartmentSelectList> deptList = jpaDepartmentService.findDeptList();
        map.put("deptList", deptList);
        return map;
    }


    @GetMapping("/detailDeptList")
    public HashMap<String, Object> findDetailDeptList(@RequestParam String workplaceCode){
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<DepartmentEntity> detailDeptList = jpaDepartmentService.findDetailDeptList(workplaceCode);
        map.put("detailDeptList", detailDeptList);
        return map;
    }
}
