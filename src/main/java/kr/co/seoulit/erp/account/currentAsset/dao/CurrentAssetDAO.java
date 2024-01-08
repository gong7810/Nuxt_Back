package kr.co.seoulit.erp.account.currentAsset.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.base.to.AccountBean;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetDetailBean;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetBean;

@Mapper
public interface CurrentAssetDAO {
//   ArrayList<CurrentAssetBean> selectCurrentAssetList(HashMap<String, Object> param);

	ArrayList<CurrentAssetBean> selectCurrentAssetListAll();
	ArrayList<CurrentAssetBean> selectCurrentAssetList(HashMap<String, String> param);

	ArrayList<CurrentAssetBean> depreciationList();
	ArrayList<CurrentAssetBean> selectedDepreciationList(String accountCode);
	ArrayList<CurrentAssetBean> fixedAssetLedgerList();


	int selectNoncurrentAssetCount(String accountCode);

	void insertCurrentAsset(HashMap<String, Object> currentAssetBean);

	void deleteCurrentAsset(String assetCode);

	void updateCurrentAsset(HashMap<String, Object> currentAssetBean);

}
