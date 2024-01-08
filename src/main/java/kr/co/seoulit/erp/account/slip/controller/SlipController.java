package kr.co.seoulit.erp.account.slip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.slip.servicefacade.SlipServiceFacade;
import kr.co.seoulit.erp.account.slip.to.SlipBean;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/acc/account/")
public class SlipController {
	@Autowired
	private SlipServiceFacade slipServiceFacade;

	//GET 전표 전체조회
	@GetMapping("/slips")
	public ResponseEntity<Map<String,Object>>  findSlipList() {
		HashMap<String, Object> map = new HashMap<>();

		ArrayList<SlipBean> SlipList = slipServiceFacade.findSlipList();
		if(SlipList.size()==0){
			return ResponseEntity.notFound().build(); //없을경우
		}

		map.put("SlipList", SlipList);

		return ResponseEntity.ok(map);
	}

	//GET 승인 / 반려 전표 조회
	@GetMapping("/slips/status/{status}")
	public ResponseEntity<Map<String,Object>>  findStatusSlipList(@PathVariable("status") String status) {
		HashMap<String, Object> map = new HashMap<>();

		ArrayList<SlipBean> statusSlipList = slipServiceFacade.findStatusSlipList(status);

		map.put("statusSlipList", statusSlipList);

		return ResponseEntity.ok(map);
	}

	//GET 미결 전표 조회
	@GetMapping("/slips/unProcessed")
	public ResponseEntity<Map<String,Object>>  findUnProSlipList() {
		HashMap<String, Object> map = new HashMap<>();

		ArrayList<SlipBean> unProSlipList = slipServiceFacade.findUnProSlipList();

		map.put("unProSlipList", unProSlipList);

		return ResponseEntity.ok(map);
	}

	//GET 전표 한개조회
	@GetMapping("/slips/{slipNo}")
	public ResponseEntity<Map<String,Object>> findSlipBySlipNo(@PathVariable String slipNo) {
		Map<String,Object> result=new HashMap<>();

		SlipBean slipBean=slipServiceFacade.findSlipBySlipNo(slipNo);
		//생성자보다는 builder패턴 사용!! 추천
		if(slipBean==null){
			return ResponseEntity.notFound().build(); //없을 경우
		}
		result.put("slipForm",slipBean);
		return ResponseEntity.ok()
				.body(result);
	}

//	//POST 전표 한개등록
	@PostMapping("/slips")
	public ResponseEntity<Map<String,Object>> registerSlip(@RequestBody SlipBean slipBean) {
		HashMap<String, Object> map = new HashMap<>();

		String slipNo = slipServiceFacade.registerSlip(slipBean);

		map.put("newSlipNo",slipNo);
		return ResponseEntity.status(HttpStatus.CREATED).body(map);
	}

	//POST 전표 리스트등록
	@PostMapping("/slipsList")
	public ResponseEntity<Map<String,Object>> registerSlipList(@RequestBody HashMap<String, ArrayList<SlipBean>> slipMap) {
		HashMap<String, Object> map = new HashMap<>();

		ArrayList<SlipBean> slipList = slipMap.get("slipList");

		String Msg = slipServiceFacade.registerSlipList(slipList);

		map.put("errorMsg",Msg);
		return ResponseEntity.status(HttpStatus.CREATED).body(map);
	}

	//DELETE 전표 리스트삭제
	@DeleteMapping("/slips")
	public ResponseEntity<Map<String,Object>> deleteSlip(@RequestBody HashMap<String, ArrayList<SlipBean>> slipMap) {
		HashMap<String, Object> map = new HashMap<>();

		ArrayList<SlipBean> slipList = slipMap.get("slipList");
		try {
			slipServiceFacade.deleteSlip(slipList);
			map.put("errorMsg","삭제되었습니다");
		}catch (Exception e){
			map.put("errorMsg","삭제실패하였습니다");
		}
		return ResponseEntity.ok(map);
	}

	//PUT 전표 리스트승인
	@PutMapping("/slipList")
	public ResponseEntity<Map<String,Object>> approvalSlip(@RequestBody HashMap<String, ArrayList<SlipBean>> slipMap) {
		HashMap<String, Object> map = new HashMap<>();

		ArrayList<SlipBean> slipList = slipMap.get("slipList");
		try {
			slipServiceFacade.approveSlip(slipList);
			map.put("updateMsg","승인/반려/취소되었습니다");
			return ResponseEntity.ok(map);
		}catch (Exception e){
			map.put("errorMsg","업데이트실패하였습니다");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
	}

	//PUT 전표 수정
	@PutMapping("/slips")
	public ResponseEntity<Map<String,Object>> updateSlip(@RequestBody SlipBean slipBean) {
		HashMap<String, Object> map = new HashMap<>();

		try {
			slipServiceFacade.updateSlip(slipBean);
			map.put("updateSlipNo",slipBean.getSlipNo());
			return ResponseEntity.ok(map);
		}catch (Exception e){
			map.put("errorMsg","업데이트실패하였습니다");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
	}

}
