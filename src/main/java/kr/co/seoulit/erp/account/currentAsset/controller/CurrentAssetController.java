package kr.co.seoulit.erp.account.currentAsset.controller;


import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.base.to.AccountBean;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetDetailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.base.to.AccountCodeBean;
import kr.co.seoulit.erp.account.currentAsset.servicefacade.CurrentAssetFacadeService;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetBean;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/*")
public class CurrentAssetController {

    @Autowired
    private CurrentAssetFacadeService currentAssetFacadeService;


    @RequestMapping(value ="/assets/getFixedAssetCodeList", method = RequestMethod.GET)
    public  ArrayList<AccountCodeBean> currentAssetCode(){
        return currentAssetFacadeService.currentAssetCode();
    }

    //감가상각비현황 전체조회
    @GetMapping("/assets/getDepreciationList")
    public ArrayList<CurrentAssetBean> depreciationList(){
        return currentAssetFacadeService.depreciationList();
    }
    //감가상각비현황 선택조회
    @RequestMapping(value = "/assets/selectedDepreciationList", method = RequestMethod.GET)
    public ArrayList<CurrentAssetBean> selectedDepreciationList(@RequestParam("accountCode") String accountCode){
        return currentAssetFacadeService.selectedDepreciationList(accountCode);
    }

    //고정자산관리대장 전체조회
    @GetMapping("/assets/getFixedAssetLedgerList")
    public ArrayList<CurrentAssetBean> fixedAssetLedgerList(){
        return currentAssetFacadeService.fixedAssetLedgerList();
    }


    //고정자산 조회
    @RequestMapping(value = "/assets/searchFixedAssetList", method = RequestMethod.GET)
    public  ArrayList<CurrentAssetBean> findCurrentAssetList(@RequestParam("fixedAssetsCode") String accountCode,
                                                             @RequestParam("fixedAssetsName") String accountName){

        return currentAssetFacadeService.findCurrentAssetList(accountCode,accountName);
    }

    //   @RequestMapping(value = "/CurrentAsset/findCurrentAssetList", method = RequestMethod.POST)
//   public  ArrayList<CurrentAssetBean> findCurrentAssetList(@RequestBody  HashMap<String, Object> params){
//
//         System.out.println("@@!!!!!!@@#@#@#"+params);
//      return currentAssetFacadeService.findCurrentAssetList(params);
//   }
    
    //고정자산 추가
    @RequestMapping(value = "/assets/addFixedAssetList", method = RequestMethod.POST)
    public void insertCurrentAsset(@RequestBody CurrentAssetBean currentAssetBean){
        currentAssetFacadeService.insertCurrentAsset(currentAssetBean);
    }
    //고정자산 삭제
    @RequestMapping(value = "/assets/deleteFixedAssetList", method = RequestMethod.DELETE)
    public void deleteCurrentAsset(@RequestParam("assetCode") String assetsCode){
        currentAssetFacadeService.deleteCurrentAsset(assetsCode);
    }


    //고정자산 상세조회
    @RequestMapping(value = "/assets/searchFixedAssetDetailList", method = RequestMethod.GET)
    public  ArrayList<CurrentAssetDetailBean> findAssetDetailList(@RequestParam("assetCode") String assetCode){
        return currentAssetFacadeService.findCurrentAssetDetailList(assetCode);
    }
}