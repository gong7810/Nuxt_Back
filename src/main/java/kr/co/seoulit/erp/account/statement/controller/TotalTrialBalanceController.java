package kr.co.seoulit.erp.account.statement.controller;

import java.util.ArrayList;
import java.util.HashMap;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.seoulit.erp.account.base.to.PeriodBean;
import kr.co.seoulit.erp.account.statement.to.TotalTrialBalance1Bean;
import kr.co.seoulit.erp.account.statement.servicefacade.StatementServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "결산", description = "결산 REST API") // Swagger 태그 추가
@OpenAPIDefinition
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"}) // Swagger 사용시 필수추가
@RequestMapping("/acc/statement")
public class TotalTrialBalanceController {
	@Autowired
	private StatementServiceFacade statementServiceFacade;

	@Operation(summary = "회계 기수 조회")
	@RequestMapping(value = "/findAccountPeriodList")
	public HashMap<String, Object> findAccountPeriodList() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			ArrayList<PeriodBean> accountPeriodList = statementServiceFacade.findAccountPeriodList();
			System.out.println("왔냐");
			map.put("accountPeriodList", accountPeriodList);
			map.put("errorCode", 0);
			map.put("errorMsg", "조회완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

	//결산 후 조회
	@Operation(summary = "시산표 결산 후 조회")
	@GetMapping(value = "/totalTrialBalances")
	public HashMap<String, Object> getAccountTotalTrialBalance(@RequestParam @Parameter(description ="회계 기수 번호") String accountPeriodNo, @RequestParam @Parameter(description ="결산 현황 여부") String callResult
	) {
		System.out.println("accountPeriodNo : " + accountPeriodNo +" , " +"callResult : " + callResult );
		HashMap<String, Object> map = new HashMap<>();
		try {
			HashMap<String, Object> totalTrialBalanceList = statementServiceFacade.getTotalTrialBalanceList(accountPeriodNo, callResult);
			map.put("totalTrialBalanceList", totalTrialBalanceList);
			map.put("errorCode", 0);
			map.put("errorMsg", "조회완료");
			System.out.println(map);
			System.out.println(totalTrialBalanceList);

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;

	}

	// 결산 후 수정
	@Operation(summary = "시산표 결산 후 수정")
	@GetMapping("/earlyStatements")
	public HashMap<String, Object> finddoClosing(@RequestParam @Parameter(description ="회계 기수 번호") String accountPeriodNo,
												 @RequestParam @Parameter(description ="결산 현황") String callResult,
												 @RequestParam @Parameter(description ="회계 년도") String periodStartDate
	) {
		System.out.println("accountPeriodNo : " + accountPeriodNo +" , " +"callResult : " + callResult );
		HashMap<String, Object> map = new HashMap<>();
		try{
			HashMap<String, Object> closingResult = statementServiceFacade.findEarlyStatements(accountPeriodNo, callResult, periodStartDate);
			map.put("closingResult", closingResult);
			map.put("errorCode", 0);
			map.put("errorMsg", "조회완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
			return map;
	}


	@Operation(summary = "결산 전 시산표 데이터 저장")
	@GetMapping ("/earlyTotaltrialBalances")
	public void findEarlyTotaltrialStatement(@RequestParam @Parameter(description="회계 기수 번호") String accountPeriodNo) {
		System.out.println("여기옴? : "+accountPeriodNo );
		System.out.println("accountPeriodNo : " +accountPeriodNo+"("+accountPeriodNo.getClass().getName()+")");
		 statementServiceFacade.findEarlyTotaltrialStatement(accountPeriodNo);
	}

//	@PostMapping("/earlyTotaltrialBalances")
//	public void findEarlyTotaltrialStatement(@RequestParam int accountPeriodNo) {
//		System.out.println("accountPeriodNo : " + accountPeriodNo);
//		statementServiceFacade.findEarlyTotaltrialStatement(accountPeriodNo);
//	}

	@Operation(summary = "결산 전 시산표 데이터 조회")
	@GetMapping("/earlyTotaltrialBalancesList")
	public HashMap<String, Object> findEarlyTotaltrialBalancesList(@RequestParam @Parameter(description="회계 기수 번호") String accountPeriodNo
	) {
		System.out.println("여기옴?1 :"+accountPeriodNo);
		System.out.println("accountPeriodNo : " + accountPeriodNo);
		HashMap<String, Object> map = new HashMap<>();
		try{
			ArrayList<TotalTrialBalance1Bean> earlyTotaltrialList = statementServiceFacade.findEarlyTotaltrialList(accountPeriodNo);
			map.put("earlyTotaltrialList", earlyTotaltrialList);
			map.put("errorCode", 0);
			map.put("errorMsg", "조회완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

	//결산
	@Operation(summary = "결산")
	@GetMapping("/statementList")
	public void saveStatementList(@RequestParam @Parameter(description="회계 기수 번호") String accountPeriodNo,
								  @RequestParam @Parameter(description="결산 현황") String callResult,
								  @RequestParam @Parameter(description="회계 년도") String periodStartDate
	) {
		System.out.println("3개 다 넘어오나? " + accountPeriodNo + ", " + callResult+", "+periodStartDate);
		statementServiceFacade.saveStatementList(accountPeriodNo, callResult, periodStartDate);
	}

	//결산 취소
	@Operation(summary = "결산 취소")
	@GetMapping("/cancelStatement")
	public HashMap<String, Object> cancelStatement(@RequestParam @Parameter(description="회계 기수 번호") String accountPeriodNo, @RequestParam @Parameter(description="결산 현황") String callResult){
		System.out.println("accountPeriodNo : " + accountPeriodNo + "callResult : " + callResult);
		HashMap<String, Object> map = new HashMap<>();
		try {
			HashMap<String,Object> earlyTotaltrialBalanceList = statementServiceFacade.cancelStatement(accountPeriodNo, callResult);
			map.put("earlyTotaltrialBalanceList", earlyTotaltrialBalanceList);
			map.put("errorCode", 0);
			map.put("errorMsg", "취소완료");
		}catch(Exception e2){
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

}
