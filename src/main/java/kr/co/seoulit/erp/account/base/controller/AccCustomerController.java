package kr.co.seoulit.erp.account.base.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.base.servicefacade.*;
import kr.co.seoulit.erp.account.base.to.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/base")
public class AccCustomerController {

	//====================================== 2020-09-01  거래처 관리  컨트롤러 생성 =====================================

	@Autowired
	private AccountServiceFacade accountServiceFacade;

	private ModelMap modelMap = new ModelMap();

	//거래처 조회
	@GetMapping(value="/customers")
	public HashMap<String,Object> getCustomerList() {
		HashMap<String,Object> map=new HashMap<>();
		map.put("accountCustomerList", accountServiceFacade.getCustomerList());

		return map;
	}

	//거래처 추가
	@RequestMapping(value="/addCustomer", method = RequestMethod.POST)
	public void batchCustomer(@RequestBody HashMap<String,ArrayList<CustomerBean>> customerList) {
		accountServiceFacade.addCustomer(customerList);
	}

	//거래처 수정
	@RequestMapping(value="/updateCustomer", method = RequestMethod.PUT)
	public void updateCustomer(@RequestBody HashMap<String,ArrayList<CustomerBean>> customerList) {
		accountServiceFacade.updateCustomer(customerList);
	}

	//거래처 삭제
	@RequestMapping(value="/deleteCustomer", method = RequestMethod.DELETE)
	public void deleteNormalCustomer(@RequestParam String customerCode) {
		accountServiceFacade.deleteNormalCustomer(customerCode);
	}

}