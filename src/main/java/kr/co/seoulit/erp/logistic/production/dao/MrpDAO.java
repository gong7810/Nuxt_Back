package kr.co.seoulit.erp.logistic.production.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.logistic.production.to.MrpTO;

@Mapper
public interface MrpDAO {

	public ArrayList<MrpTO> selectMrpListAll(String mrpGatheringStatusCondition);

	public ArrayList<MrpTO> selectMrpListAsMrpGatheringNo(String mrpGatheringNo);

	public List<MrpTO> selectMrpCount(String mrpRegisterDate);

	public void insertMrp(MrpTO TO);

	public void updateMrp(MrpTO TO);

	public void deleteMrp(MrpTO TO);

	public ArrayList<MrpTO> selectMrpList(HashMap<String, String> param);

	public void openMrp(HashMap<String, Object> resultMap);

	public void changeMrpGatheringStatus(HashMap<String, String> param);

}
