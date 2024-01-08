package kr.co.seoulit.erp.account.funds.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.co.seoulit.erp.account.funds.to.DailyTradeStatusBean;
import kr.co.seoulit.erp.account.funds.to.FinanceBean;
import kr.co.seoulit.erp.account.funds.to.InoutBean;
import kr.co.seoulit.erp.account.funds.to.PlanBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.funds.servicefacade.FundServiceFacade;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/funds/")
public class FundController {
    @Autowired
    private FundServiceFacade fundServiceFacade;

    //POST 일자별자금계획 추가
    @PostMapping("/dailyFundPlan")
    public ResponseEntity<Map<String, Object>> registerPlan(@RequestBody PlanBean planBean) {
        HashMap<String, Object> map = new HashMap<>();

        String planNo = fundServiceFacade.registerPlan(planBean);

        map.put("planNo",planNo);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    //GET 일자별자금계획 전체조회
    @GetMapping("/dailyFundPlan")
    public ResponseEntity<Map<String, Object>> getDailyFundPlan(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        HashMap<String, Object> map = new HashMap<>();

        HashMap<String, ArrayList<PlanBean>> dailyFundPlanMap = fundServiceFacade.getDailyFundPlan(startDate, endDate);
        if(dailyFundPlanMap.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        map.put("inExpectedPlanList", dailyFundPlanMap.get("inExpectedPlanList"));
        map.put("outExpectedPlanList", dailyFundPlanMap.get("outExpectedPlanList"));

        return ResponseEntity.ok(map);
    }

    //DELETE 일자별자금계획 삭제
    @DeleteMapping("/dailyFundPlan/{planNo}")
    public ResponseEntity<Map<String, Object>> deleteDailyFundPlan(@PathVariable String planNo) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            fundServiceFacade.deleteDailyFundPlan(planNo);
            map.put("errorMsg","삭제되었습니다");
        }catch (Exception e){
            map.put("errorMsg","삭제실패하였습니다");
        }
        return ResponseEntity.ok(map);
    }

    //PUT 일자별자금계획 수정
    @PutMapping("/dailyFundPlan")
    public ResponseEntity<Map<String, Object>> updateDailyFundPlan(@RequestBody PlanBean planBean) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            fundServiceFacade.updateDailyFundPlan(planBean);
            map.put("errorMsg","수정되었습니다");
        }catch (Exception e){
            map.put("errorMsg","수정실패하였습니다");
        }
        return ResponseEntity.ok(map);
    }

    //GET 자금계획 카렌다 날짜별 수입조회
    @GetMapping("/calendarIncome/{day}")
    public ResponseEntity<Map<String, String>> getCalendarIncome(@PathVariable String day) {
        HashMap<String, String> map = new HashMap<>();

        String income = fundServiceFacade.getCalendarIncome(day);

        map.put("price", income);

        return ResponseEntity.ok(map);
    }

    //GET 자금계획 카렌다 날짜별 지출조회
    @GetMapping("/calendarExpenditure/{day}")
    public ResponseEntity<Map<String, String>> getCalendarExpenditure(@PathVariable String day) {
        HashMap<String, String> map = new HashMap<>();

        String expenditure = fundServiceFacade.getCalendarExpenditure(day);

        map.put("price", expenditure);

        return ResponseEntity.ok(map);
    }

    //GET 자금계획 카렌다 날짜별 계획상세조회
    @GetMapping("/calendar/{date}")
    public ResponseEntity<Map<String, Object>> getCalendarDetail(@PathVariable String date) {
        HashMap<String, Object> map = new HashMap<>();

        HashMap<String, ArrayList<PlanBean>> CalendarDetailMap = fundServiceFacade.getCalendarDetail(date);
        if(CalendarDetailMap.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        map.put("inExpectedPlanList", CalendarDetailMap.get("inExpectedPlanList"));
        map.put("outExpectedPlanList", CalendarDetailMap.get("outExpectedPlanList"));

        return ResponseEntity.ok(map);
    }

    //GET 일일거래증감현황 조회
    @GetMapping("/dailyTradeStatus")
    public ResponseEntity<Map<String, Object>> getDailyTradeStatus(@RequestParam("date") String date) {
        HashMap<String, Object> map = new HashMap<>();

        HashMap<String, Object> dailyTradeStatusMap = fundServiceFacade.getDailyTradeStatus(date);
        if(dailyTradeStatusMap.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        ArrayList<DailyTradeStatusBean> dailyTradeStatusList = (ArrayList<DailyTradeStatusBean>) dailyTradeStatusMap.get("RESULT");

        map.put("dailyTradeStatusList", dailyTradeStatusList);

        return ResponseEntity.ok(map);
    }

    //GET 입출금예정액 조회
    @GetMapping("/inoutExpectedPrice")
    public ResponseEntity<Map<String, ArrayList<InoutBean>>> getInoutExpectedPrice(@RequestParam("date") String date) {

        HashMap<String, ArrayList<InoutBean>> inoutExpectedPriceMap = fundServiceFacade.getInoutExpectedPrice(date);
        if(inoutExpectedPriceMap.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        return ResponseEntity.ok(inoutExpectedPriceMap);
    }

    //GET 예적금현황 조회
    @GetMapping("/financeStatus/{accountName}")
    public ResponseEntity<Map<String, Object>> getFinanceStatus(@PathVariable("accountName") String accoutName, @RequestParam String date) {
        HashMap<String, Object> map = new HashMap<>();

        HashMap<String, Object> financeStatusMap = fundServiceFacade.getFinanceStatus(date, accoutName);
        if(financeStatusMap.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        ArrayList<FinanceBean> financeStatusList = (ArrayList<FinanceBean>) financeStatusMap.get("RESULT");

        map.put("financeStatusList", financeStatusList);

        return ResponseEntity.ok(map);
    }

    //GET 총괄거래현황 조회
    @GetMapping("/generalFundStatus/{startDate}/{endDate}")
    public ResponseEntity<Map<String, Object>> getGeneralFundStatus(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        HashMap<String, Object> map = new HashMap<>();

        HashMap<String, Object> generalFundStatusMap = fundServiceFacade.getGeneralFundStatus(startDate, endDate);
        if(generalFundStatusMap.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        return ResponseEntity.ok(generalFundStatusMap);
    }
}
