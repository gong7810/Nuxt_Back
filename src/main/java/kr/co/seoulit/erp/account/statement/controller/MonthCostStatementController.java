package kr.co.seoulit.erp.account.statement.controller;

import kr.co.seoulit.erp.account.statement.servicefacade.StatementServiceFacade;
import kr.co.seoulit.erp.account.statement.to.MonthCostStatementBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/statement")
public class MonthCostStatementController {

    @Autowired
    private StatementServiceFacade statementServiceFacade;

    @GetMapping(value = "/monthcosts")
    public HashMap<String, Object> handleRequestInternal(@RequestParam("searchDate") String toDate) {

        System.out.println(" 기간별원가????????????????????????????");
        HashMap<String, Object> map = new HashMap<>();
        try {
            ArrayList<MonthCostStatementBean> monthCostList = statementServiceFacade.getMonthCostStatement(toDate);
            map.put("monthCostList", monthCostList);
        } catch (Exception e) {
            map.put("errorCode", -1);
            map.put("errorMsg", e.getMessage());
        }
        return map;
    }
}