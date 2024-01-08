package kr.co.seoulit.erp.account.statement.controller;

import java.util.ArrayList;
import java.util.HashMap;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.seoulit.erp.account.statement.to.MonthIncomeStatementBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.statement.servicefacade.StatementServiceFacade;

@Tag(name = "월별 손익계산서", description="월별 손익계산서 REST API")
@CrossOrigin("*")
@OpenAPIDefinition
@RestController
@RequestMapping("/acc/statement")
public class MonthIncomeStatementController {

	@Autowired
	private StatementServiceFacade statementServiceFacade;

//================================ 손익계산서 컨드롤러  ====================================

//	@GetMapping(value = "/monthIncomeStatements")
//	public ResponseEntity<HashMap<String, Object>> handleRequestInternal(@RequestParam("searchDate") String toDate) {
//
//		System.out.println("============월별손익계산서 컨트롤러시작===============");
//		HashMap<String, Object> param = new HashMap<>();
//		try {
//
//			param = statementServiceFacade.getMonthIncomeStatement(toDate);
//			param.put("monthIncomeList", param.get("RESULT"));
//			return ResponseEntity.ok(param);
//
//
//		} catch (Exception e) {
//			param.put("errorCode", -1);
//			param.put("errorMsg", e.getMessage());
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}
//
//	}

	@Operation(summary = "월별 손익계산서 결산 조회")
	@GetMapping(value = "/monthIncomeStatements")
	public HashMap<String, Object> handleRequestInternal(@RequestParam("accountPeriodNo") @Parameter(description="회계 기수 번호") String accountPeriodNo
																		) {

		System.out.println("============월별손익계산서 컨트롤러시작===============");
		HashMap<String, Object> map = new HashMap<>();
		try {
			ArrayList<MonthIncomeStatementBean> monthIncomeList = statementServiceFacade.getMonthIncomeStatement(accountPeriodNo);
			map.put("monthIncomeList", monthIncomeList);
		} catch (Exception e) {
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}

}
