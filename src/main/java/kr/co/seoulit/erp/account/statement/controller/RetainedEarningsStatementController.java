package kr.co.seoulit.erp.account.statement.controller;

import kr.co.seoulit.erp.account.statement.servicefacade.StatementServiceFacade;
import kr.co.seoulit.erp.account.statement.to.RetainedEarningsStatementBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/statement")
public class RetainedEarningsStatementController {

    @Autowired
    private StatementServiceFacade statementServiceFacade;

    @GetMapping(value = "/retainedEarningsStatements")
    public HashMap<String, Object> handleRequestInternal(@RequestParam("searchDate") String toDate) {

        HashMap<String, Object> map = new HashMap<>();
        try {
            ArrayList<RetainedEarningsStatementBean> retainedEarningsList = statementServiceFacade.getRetainedEarningsStatement(toDate);
            map.put("retainedEarningsList", retainedEarningsList);
        } catch (Exception e) {
            map.put("errorCode", -1);
            map.put("errorMsg", e.getMessage());
        }
        return map;
    }
}