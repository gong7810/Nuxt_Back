package kr.co.seoulit.erp.account.currentAsset.servicefacade;


import kr.co.seoulit.erp.account.base.to.AccountBean;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetDetailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.erp.account.base.to.AccountCodeBean;
import kr.co.seoulit.erp.account.currentAsset.applicationservice.CurrentAssetApplicationService;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetBean;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CurrentAssetFacadeServiceImpl  implements CurrentAssetFacadeService{
	@Autowired
	private CurrentAssetApplicationService currentAssetApplicationService;
	@Override
	public ArrayList<AccountCodeBean> currentAssetCode(){
		return currentAssetApplicationService.currentAssetCode();
	}

	@Override
	public ArrayList<CurrentAssetBean> depreciationList(){
		return currentAssetApplicationService.depreciationList();
	}
	@Override
	public ArrayList<CurrentAssetBean> selectedDepreciationList(String accountCode){
		return currentAssetApplicationService.selectedDepreciationList(accountCode);
	}

	public ArrayList<CurrentAssetBean> fixedAssetLedgerList(){
		return currentAssetApplicationService.fixedAssetLedgerList();
	}


	@Override
	public ArrayList<CurrentAssetBean> findCurrentAssetList(String accountCode, String accountName){
		return currentAssetApplicationService.findCurrentAssetList(accountCode, accountName);
	}

	@Override
	public ArrayList<CurrentAssetDetailBean> findCurrentAssetDetailList(String assetCode){
		return currentAssetApplicationService.findCurrentAssetDetailList(assetCode);
	}
	//   @Override
//   public ArrayList<CurrentAssetBean> findCurrentAssetList(HashMap<String, Object> params){
//      return currentAssetApplicationService.findCurrentAssetList(params);
//   }
	@Override
	public void insertCurrentAsset(CurrentAssetBean currentAssetBean) {

		HashMap<String, Object> params=new HashMap<>();
		params.put("accountCode",currentAssetBean.getAccountCode());
		params.put("accountName",currentAssetBean.getAccountName());
		params.put("assetCode",currentAssetBean.getAssetCode());
		params.put("assetName",currentAssetBean.getAssetName());
		params.put("progress",currentAssetBean.getProgress());
		params.put("finalizeStatus",currentAssetBean.getFinalizeStatus());
		params.put("checkStatus",currentAssetBean.getCheckStatus());
		params.put("currentAssetDetailBean",currentAssetBean.getCurrentAssetDetailBean());

		for (CurrentAssetDetailBean detailBean : currentAssetBean.getCurrentAssetDetailBean()) {
			params.put("assetCode", detailBean.getAssetCode());
			params.put("acquisitionCost", detailBean.getAcquisitionCost());
			params.put("depreciation", detailBean.getDepreciation());
			params.put("bookValue", detailBean.getBookValue());
			params.put("amortizationWay", detailBean.getAmortizationWay());
			params.put("amortizationFinalYear", detailBean.getAmortizationFinalYear());
			params.put("acquisitionQuantity", detailBean.getAcquisitionQuantity());
			params.put("changeQuantity", detailBean.getChangeQuantity());
			params.put("remainQuantity", detailBean.getRemainQuantity());
			params.put("department", detailBean.getDepartment());
			params.put("usefulLife", detailBean.getUsefulLife());
			params.put("amortizationRate", detailBean.getAmortizationRate());
			params.put("month", detailBean.getMonth());
			params.put("normalAmortization", detailBean.getNormalAmortization());
			params.put("RAccumulatedAmortization", detailBean.getRAccumulatedAmortization());
			params.put("accumulatedAmortization", detailBean.getAccumulatedAmortization());
			params.put("bookValueEnd", detailBean.getBookValueEnd());
		}

		currentAssetApplicationService.insertCurrentAsset(params);
	}

	@Override
	public void deleteCurrentAsset(String assetCode){
		currentAssetApplicationService.deleteCurrentAsset(assetCode);
	}
}