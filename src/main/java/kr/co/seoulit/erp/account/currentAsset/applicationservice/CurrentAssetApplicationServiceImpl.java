package kr.co.seoulit.erp.account.currentAsset.applicationservice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.co.seoulit.erp.account.base.dao.AccountDAO;
import kr.co.seoulit.erp.account.base.to.AccountBean;
import kr.co.seoulit.erp.account.base.to.AccountCodeBean;
import kr.co.seoulit.erp.account.currentAsset.dao.CurrentAssetDAO;
import kr.co.seoulit.erp.account.currentAsset.dao.CurrentAssetDetailDAO;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetBean;
import kr.co.seoulit.erp.account.currentAsset.to.CurrentAssetDetailBean;


@Component
public class CurrentAssetApplicationServiceImpl implements  CurrentAssetApplicationService{


	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private CurrentAssetDAO currentAssetDAO;
	@Autowired
	private CurrentAssetDetailDAO currentAssetDetailDAO;

	@Override
	public ArrayList<AccountCodeBean> currentAssetCode(){
		return accountDAO.selectCurrentAssetList();
	}

	@Override
	public ArrayList<CurrentAssetBean> depreciationList(){
        return currentAssetDAO.depreciationList();
	}
	@Override
	public ArrayList<CurrentAssetBean> selectedDepreciationList(String accountCode){
        return currentAssetDAO.selectedDepreciationList(accountCode);
	}
	@Override
	public ArrayList<CurrentAssetBean> fixedAssetLedgerList(){
		return currentAssetDAO.fixedAssetLedgerList();
	}


	@Override
	public ArrayList<CurrentAssetBean> findCurrentAssetList(String accountCode, String accountName){
		HashMap<String, String> param=new HashMap<>();
		ArrayList<CurrentAssetBean> findCurrentAssetList=new ArrayList<>();


		if(accountName.equals("all")){
			findCurrentAssetList=currentAssetDAO.selectCurrentAssetListAll();
		}else {
			param.put("accountCode", accountCode);
			param.put("accountName", accountName);

			findCurrentAssetList = currentAssetDAO.selectCurrentAssetList(param);
		}
		return findCurrentAssetList;
	}

	@Override
	public ArrayList<CurrentAssetDetailBean> findCurrentAssetDetailList(String assetCode){
		HashMap<String, String> param=new HashMap<>();
		param.put("assetCode", assetCode);

        return currentAssetDetailDAO.selectCurrentAssetDetailList(param);
	}
	//   @Override
//   public ArrayList<CurrentAssetBean> findCurrentAssetList(HashMap<String, Object> params){
//      System.out.println("@@##!3!:::::"+params.get("params"));
//
//      HashMap<String, Object> param =new HashMap<String, Object>();
//      param=(HashMap<String, Object>) params.get("params");
//
//      ArrayList<CurrentAssetBean> findCurrentAssetList=currentAssetDAO.selectCurrentAssetList(param);
//       System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@"+findCurrentAssetList);
//       return currentAssetDAO.selectCurrentAssetList(param);
//   }
	@Override
	public void insertCurrentAsset(HashMap<String, Object> params) {

		String checkStatus = (String)params.get("checkStatus");

		if(checkStatus.equals("insert")) {


			currentAssetDAO.insertCurrentAsset(params);
      		currentAssetDetailDAO.insertCurrentAssetDetail(params);

		//currentAssetDetailBean.put("assetCode",assetCode.toString());


		}if(checkStatus.equals("update")) {
			currentAssetDAO.updateCurrentAsset(params);
			currentAssetDetailDAO.updateCurrentAssetDetail(params);

		//currentAssetDetailBean.put("assetCode",params.get("assetCode").toString());


		}
	}
	@Override
	public void deleteCurrentAsset(String assetCode){
		currentAssetDAO.deleteCurrentAsset(assetCode);
		currentAssetDetailDAO.deleteCurrentAssetDetail(assetCode);
	}
}