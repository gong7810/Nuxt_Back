package kr.co.seoulit.erp.logistic.production.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.logistic.production.to.MpsTO;

@Mapper
public interface MpsDAO {

	public List<MpsTO> selectMpsCount(String mpsPlanDate);

	public void insertMps(MpsTO TO);

	public void updateMps(MpsTO TO);

	public void deleteMps(MpsTO TO);

	public ArrayList<MpsTO> selectMpsList(HashMap<String, String> param);

	public void changeMrpApplyStatus(HashMap<String, String> param);

}
