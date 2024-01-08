package kr.co.seoulit.erp.account.currentAsset.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetDetailBean;

@Mapper
public interface CurrentAssetDetailDAO {
	ArrayList<CurrentAssetDetailBean> selectCurrentAssetDetailList(HashMap<String, String> param);
	void insertCurrentAssetDetail(HashMap<String, Object> currentAssetDetailBean);
	void deleteCurrentAssetDetail(String assetCode);
	void updateCurrentAssetDetail(HashMap<String, Object> currentDetailAssetBean);
}