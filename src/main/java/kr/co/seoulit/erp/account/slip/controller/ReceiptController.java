package kr.co.seoulit.erp.account.slip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.co.seoulit.erp.account.slip.to.ReceiptBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.slip.servicefacade.SlipServiceFacade;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/account/")
public class ReceiptController {
    @Autowired
    private SlipServiceFacade slipServiceFacade;

    //GET 지출증빙 전체조회
    @GetMapping("/receipt")
    public ResponseEntity<Map<String, Object>>  findReceiptList() {
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<ReceiptBean> receiptList = slipServiceFacade.findReceiptList();
        if(receiptList.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        map.put("receiptList", receiptList);

        return ResponseEntity.ok(map);
    }

    //GET 지출증빙 한개조회
    @GetMapping("/receipt/{slipNo}")
    public ResponseEntity<Map<String, Object>> findReceiptBySlipNo(@PathVariable String slipNo) {
        Map<String,Object> result=new HashMap<>();

        ReceiptBean receiptBean = slipServiceFacade.findReceiptBySlipNo(slipNo);
        //생성자보다는 builder패턴 사용!! 추천
        if(receiptBean==null){
            return ResponseEntity.notFound().build(); //없을 경우
        }
        result.put("receiptForm",receiptBean);
        return ResponseEntity.ok()
                .body(result);
    }

    //POST 지출증빙 증빙등록
    @PostMapping("/receipt/{slipNo}/{type}")
    public ResponseEntity<Map<String, Object>> registerReceiptFile(@PathVariable String slipNo, @PathVariable String type, @RequestPart MultipartFile file) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            slipServiceFacade.registerReceipt(slipNo, type, file);

            map.put("errorMsg","증빙등록되었습니다");
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("errorMsg","증빙등록 실패하였습니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    //DELETE 지출증빙 증빙삭제
    @DeleteMapping("/receipt/{slipNo}")
    public ResponseEntity<Map<String, Object>> deleteReceiptFile(@PathVariable String slipNo) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            slipServiceFacade.deleteReceiptFile(slipNo);

            map.put("errorMsg","증빙파일 삭제되었습니다");
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("errorMsg","파일 삭제 실패하였습니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    //PUT 지출증빙 리스트증빙완료
    @PutMapping("/receipt")
    public ResponseEntity<Map<String, Object>> proofReceipt(@RequestBody HashMap<String, ArrayList<ReceiptBean>> receiptMap) {
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<ReceiptBean> receiptList = receiptMap.get("receiptList");
        try {
            slipServiceFacade.proofReceipt(receiptList);
            map.put("updateMsg","증빙완료/ 상태미흡 처리되었습니다");
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("errorMsg","증빙실패되었습니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

}
