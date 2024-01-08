package kr.co.seoulit.erp.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.statement.to.RetainedEarningsStatementBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RetainedEarningsStatementDAO {

    // 이익잉여금처분계산서 조회
    ArrayList<RetainedEarningsStatementBean> selectRetainedEarningsList(HashMap<String, String> param);
}
