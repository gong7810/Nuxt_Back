package kr.co.seoulit.erp.account.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.erp.account.base.applicationservice.JpaAccountSerivceImpl;
import kr.co.seoulit.erp.account.base.entity.AccountEntity;
import kr.co.seoulit.erp.account.base.to.LedgerByAccountBean;
import kr.co.seoulit.erp.account.slip.to.GeneralAccountLedgerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.base.servicefacade.AccountServiceFacade;
import kr.co.seoulit.erp.account.base.to.AccountBean;
import kr.co.seoulit.erp.account.base.to.AccountCodeBean;
import kr.co.seoulit.erp.account.base.to.PeriodBean;


@CrossOrigin("*")
@RestController
@RequestMapping("/acc/base/*")
public class 	AccountController {
	@Autowired
	private AccountServiceFacade accountServiceFacade;

	@Autowired
	private JpaAccountSerivceImpl jpaAccountService;


	//계정별원장조회
	@GetMapping( "/accounts/{accountCode}")
	public ResponseEntity<HashMap<String, Object>> getLedgerbyAccountInfo(@PathVariable String accountCode,
																		  @RequestParam("startDate") String startDate
			, @RequestParam("endDate") String endDate) {

		HashMap<String, Object> param = new HashMap<>();
		HashMap<String, Object> resultMap = accountServiceFacade.getLedgerbyAccountInfo(accountCode, startDate, endDate);

		ArrayList<LedgerByAccountBean> ledgerList= (ArrayList<LedgerByAccountBean>) resultMap.get("RESULT");
		if(ledgerList.size()==0){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(param);
		}

		param.put("accountLedgerList", ledgerList);
		return ResponseEntity.ok(param);
	}


	//총계정원장
	@GetMapping("/accounts")
	public ResponseEntity<ArrayList<GeneralAccountLedgerBean>> findGeneralAccountLedgerList(
			@RequestParam("startDate") String fromDate,
			@RequestParam("endDate") String toDate) {
		ArrayList<GeneralAccountLedgerBean> generalAccountLedgerList = accountServiceFacade.findGeneralAccountLedgerList(fromDate, toDate);

		if(generalAccountLedgerList.size()==0){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(generalAccountLedgerList);
	}


	//계정코드 전체조회
	@GetMapping( "/accountCodes")
	public HashMap<String, Object> getAccountList()  {


		HashMap<String, Object> map = new HashMap<>();
		map.put("accountCodeList", accountServiceFacade.getAccountList());
		return map;
	}

	//계정코드 조건조회
	@GetMapping("/accountCodes/{accountCode}")
	public HashMap<String,Object> getAccount(@PathVariable("accountCode") String accountCode,
											 @RequestParam String accountName) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("accountCodeList",accountServiceFacade.getAccount(accountCode, accountName));
		return map;
	}

	//계정과목 삭제
	@DeleteMapping("/accountCodes/{accountInnerCode}")
	public ResponseEntity<HashMap<String,Object>> deleteAccountList(@PathVariable("accountInnerCode") String accountInnerCode) {
		HashMap<String,Object> map=new HashMap<>();
		try {
			accountServiceFacade.deleteAccountList(accountInnerCode);
			map.put("updateCodeNo", accountInnerCode);

			return ResponseEntity.ok(map);
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	//계정과목 저장
	@PostMapping("/accountCodes")
	public ResponseEntity<HashMap<String,Object>> batchAccountList(@RequestBody AccountBean accountBean) {

		HashMap<String,Object> map=new HashMap<>();
		try{
			accountServiceFacade.registerAccountCode(accountBean);
			map.put("errorMsg","등록에 성공하셨습니다");
			return  ResponseEntity.ok(map);
		}catch(Exception ex){
			map.put("errorMsg","에러가 발생하였습니다");
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}

	}

	@RequestMapping(value = "/account/getAccountListByName")
	public HashMap<String, Object> getAccountListByName(@RequestParam("accountName") String accountName) {

		HashMap<String, Object> map = new HashMap<>();
		try {
			ArrayList<AccountBean> accountBean = accountServiceFacade.getAccountListByName(accountName);
			map.put("accountList", accountBean);
			map.put("errorCode", 0);
			map.put("errorMsg", "등록완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/account/findParentAccountList", method = RequestMethod.GET)
	public HashMap<String, Object> findParentAccountList() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			ArrayList<AccountBean> accountBean = accountServiceFacade.findParentAccountList();
			map.put("accountList", accountBean);
			map.put("errorCode", 0);
			map.put("errorMsg", "등록완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/account/findDetailAccountList", method = RequestMethod.GET)
	public HashMap<String, Object> findDetailAccountList(@RequestParam("code") String code) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			ArrayList<AccountBean> accountBean = accountServiceFacade.findDetailAccountList(code);
			map.put("detailAccountList", accountBean);
			map.put("errorCode", 0);
			map.put("errorMsg", "등록완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/account/editAccount", method = RequestMethod.PUT)
	public void editAccount(@RequestParam HashMap<String, Object> param) {
		AccountBean accountBean = new AccountBean();
		accountBean.setAccountInnerCode((String) param.get("accountInnerCode"));
		accountBean.setAccountName((String) param.get("accountName"));
		accountServiceFacade.updateAccount(accountBean);
	}

	@RequestMapping(value = "/account/findPeriodNo")
	public String findPeriodNo(@RequestParam String toDay) {

		String periodNo = accountServiceFacade.findPeriodNo(toDay);
		return periodNo;
	}

	@RequestMapping(value = "/account/findDetailBudgetList")
	public HashMap<String, Object> findDetailBudgetList(@RequestParam("budgetCode") String code) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			ArrayList<AccountBean> budgetList = accountServiceFacade.findDetailBudgetList(code);
			map.put("detailBudgetList", budgetList);
			map.put("errorCode", 0);
			map.put("errorMsg", "조회완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/account/findParentBudgetList")
	public HashMap<String, Object> findParentBudgetList() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			ArrayList<AccountBean> parentBudgetList = accountServiceFacade.findParentBudgetList();
			map.put("parentBudgetList", parentBudgetList);
			map.put("errorCode", 0);
			map.put("errorMsg", "조회완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/account/findAccountPeriodList")
	public HashMap<String, Object> findAccountPeriodList() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			ArrayList<PeriodBean> accountPeriodList = accountServiceFacade.findAccountPeriodList();
			map.put("accountPeriodList", accountPeriodList);
			map.put("errorCode", 0);
			map.put("errorMsg", "조회완료");

		} catch (Exception e2) {
			map.put("errorCode", -1);
			map.put("errorMsg", e2.getMessage());
		}
		return map;
	}

	//계정과목조회
	@GetMapping("/parentAccountList")
	public HashMap<String, Object> getAccountSubjectList() {
		String accountInnerCode = "%-%";
		String s = "0101-0250"; String v = "0101-1000";
		ArrayList<String> parentAccountInnerCode = new ArrayList<String>();
		parentAccountInnerCode.add(s); parentAccountInnerCode.add(v);

		HashMap<String, Object> map = new HashMap<>();


		System.out.println(jpaAccountService.findParentAccountList(accountInnerCode, parentAccountInnerCode));

		ArrayList<AccountEntity> accountCodeList =  jpaAccountService.findParentAccountList(accountInnerCode, parentAccountInnerCode);
		map.put("accountCodeList",accountCodeList);
		System.out.println("accountList 넘어와라"+map);
		return map;
	}

}
