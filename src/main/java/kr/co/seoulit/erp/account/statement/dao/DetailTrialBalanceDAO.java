package kr.co.seoulit.erp.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.account.statement.to.DetailTrialBalanceBean;


@Mapper
public interface DetailTrialBalanceDAO {

   ArrayList<DetailTrialBalanceBean> selectDetailTrialBalance(HashMap<String, String> param);

}