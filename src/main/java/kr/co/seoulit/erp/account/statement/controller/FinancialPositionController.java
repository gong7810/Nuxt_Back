package kr.co.seoulit.erp.account.statement.controller;

import java.util.HashMap;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.statement.servicefacade.StatementServiceFacade;

@Tag(name = "재무상태표", description="재무상태표 REST API")
@CrossOrigin("*")
@OpenAPIDefinition
@RestController
@RequestMapping("/acc/statement")
public class FinancialPositionController {
    @Autowired
	private StatementServiceFacade statementServiceFacade;

//    @GetMapping("/financialPositions")
//    public ResponseEntity<HashMap<String, Object>> getFinancialPosition(@RequestParam("searchDate")String toDate){
//
//    HashMap<String, Object> param =new HashMap<>();
//	try {
//
//        HashMap<String, Object> result = statementServiceFacade.getFinancialPosition(toDate);
//
//		param.put("financialPositionList", result.get("RESULT"));
//
//        return ResponseEntity.ok(param);
//		} catch(Exception e){
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	           }
//
//    }

    @Operation(summary = "재무상태표 결산 조회")
    @GetMapping("/financialPositions")
    public HashMap<String, Object> getFinancialPosition(@RequestParam("accountPeriodNo") @Parameter(description="회계 기수 번호") String accountPeriodNo,
                                                                        @RequestParam("callResult") @Parameter(description="결산 현황") String callResult) {

        HashMap<String, Object> map = new HashMap<>();
        try {
            HashMap<String, Object> financialPositionList = statementServiceFacade.getFinancialPosition(accountPeriodNo, callResult);
            map.put("financialPositionList", financialPositionList);
        } catch (Exception e) {
            map.put("errorCode", -1);
            map.put("errorMsg", e.getMessage());
        }

        return map;
    }

    @RequestMapping(value="/addearlystatements", method = RequestMethod.POST)
	public void addearlystatements(@RequestParam("toDate")String toDate) {

          //  HashMap<String, Object> financialPosition = statementServiceFacade.addEarlyStatements(toDate);
          statementServiceFacade.addEarlyStatements(toDate);
    }

}

