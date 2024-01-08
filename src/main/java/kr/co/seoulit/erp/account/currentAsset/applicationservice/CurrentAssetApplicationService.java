package kr.co.seoulit.erp.account.currentAsset.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.base.to.AccountBean;
import kr.co.seoulit.erp.account.base.to.AccountCodeBean;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetBean;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetDetailBean;


public interface CurrentAssetApplicationService {
	public ArrayList<AccountCodeBean> currentAssetCode();

	public ArrayList<CurrentAssetBean> depreciationList();
	public ArrayList<CurrentAssetBean> selectedDepreciationList(String accountCode);
	public ArrayList<CurrentAssetBean> fixedAssetLedgerList();

	public ArrayList<CurrentAssetBean> findCurrentAssetList(String accountCode, String accountName);
//   public ArrayList<CurrentAssetBean> findCurrentAssetList(HashMap<String, Object> params);

	public ArrayList<CurrentAssetDetailBean> findCurrentAssetDetailList(String assetCode);

	public void insertCurrentAsset(HashMap<String, Object> params);

	void deleteCurrentAsset(String assetCode);


}