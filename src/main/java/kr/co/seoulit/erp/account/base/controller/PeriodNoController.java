package kr.co.seoulit.erp.account.base.controller;

import kr.co.seoulit.erp.account.base.applicationservice.JpaPeriodService;
import kr.co.seoulit.erp.account.base.entity.PeriodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*2023-04-14 은비*/

@CrossOrigin("*")
@RequestMapping("/acc/budget/*")
@RestController
public class PeriodNoController {
    @Autowired
    private JpaPeriodService jpaPeriodService;

    @GetMapping("/periodNoList")
    public HashMap<String, Object> findPeriodNo(){
        HashMap<String, Object> map = new HashMap<>();

        System.out.println("야 되냐고 ~~~~~");
        ArrayList<PeriodEntity> periodEntity = jpaPeriodService.findPeriodNo();
        map.put("periodList",periodEntity);
        System.out.println("~~~~~map~~~~~~"+map);
        return map;
    }
}
