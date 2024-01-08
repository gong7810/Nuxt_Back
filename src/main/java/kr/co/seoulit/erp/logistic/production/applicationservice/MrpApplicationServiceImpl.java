package kr.co.seoulit.erp.logistic.production.applicationservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.erp.logistic.production.dao.MpsDAO;
import kr.co.seoulit.erp.logistic.production.dao.MrpDAO;
import kr.co.seoulit.erp.logistic.production.dao.MrpGatheringDAO;
import kr.co.seoulit.erp.logistic.production.to.MrpGatheringTO;
import kr.co.seoulit.erp.logistic.production.to.MrpTO;

@Component
public class MrpApplicationServiceImpl implements MrpApplicationService {

	@Autowired
	private MpsDAO mpsDAO;
	@Autowired
	private MrpDAO mrpDAO;
	@Autowired
	private MrpGatheringDAO mrpGatheringDAO;

	public ArrayList<MrpTO> searchMrpList(String mrpGatheringStatusCondition) {

		return mrpDAO.selectMrpListAll(mrpGatheringStatusCondition);
	}

	public ArrayList<MrpTO> searchMrpList(String dateSearchCondtion, String startDate, String endDate) {

		HashMap<String, String> param = new HashMap<>();
		param.put("dateSearchCondtion", dateSearchCondtion);
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		return mrpDAO.selectMrpList(param);

	}

	public ArrayList<MrpTO> searchMrpListAsMrpGatheringNo(String mrpGatheringNo) {

		return mrpDAO.selectMrpListAsMrpGatheringNo(mrpGatheringNo);

	}

	public ArrayList<MrpGatheringTO> searchMrpGatheringList(String dateSearchCondtion, String startDate,
			String endDate) {

		ArrayList<MrpGatheringTO> mrpGatheringList = null;
		HashMap<String, String> param = new HashMap<>();
		param.put("dateSearchCondtion", dateSearchCondtion);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		System.out.println("as  :  " + dateSearchCondtion);
		mrpGatheringList = mrpGatheringDAO.selectMrpGatheringList(param);

		for (MrpGatheringTO bean : mrpGatheringList) {

			bean.setMrpTOList(mrpDAO.selectMrpListAsMrpGatheringNo(bean.getMrpGatheringNo()));

		}

		return mrpGatheringList;

	}

	public HashMap<String, Object> openMrp(ArrayList<String> mpsNoArr) {

		HashMap<String, Object> resultMap = new HashMap<>();

		String mpsNoList = mpsNoArr.toString().replace("[", "").replace("]", "");
		HashMap<String, Object> param = new HashMap<>();
		param.put("mpsNoList", mpsNoList);
		mrpDAO.openMrp(param);
		resultMap.put("gridRowJson", param.get("RESULT"));
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));
		System.out.println("�씠嫄곕뒗        ");
		System.out.println(resultMap.get("gridRowJson"));
		return resultMap;

	}

	public HashMap<String, Object> registerMrp(String mrpRegisterDate, ArrayList<MrpTO> newMrpList) {

		HashMap<String, Object> resultMap = null;
		HashMap<String, String> param = null;

		List<MrpTO> mrpTOList = mrpDAO.selectMrpCount(mrpRegisterDate);
		TreeSet<Integer> intSet = new TreeSet<>();

		int i;

		for (MrpTO bean : mrpTOList) {

			String mrpNo = bean.getMrpNo();

			// MRP �씪�젴踰덊샇�뿉�꽌 留덉�留� 3�옄由щ쭔 媛��졇�삤湲�
			int no = Integer.parseInt(mrpNo.substring(mrpNo.length() - 3, mrpNo.length()));

			intSet.add(no);

		}

		if (intSet.isEmpty()) {
			i = 1;
		} else {
			i = intSet.pollLast() + 1; // 媛��옣 �넂�� 踰덊샇 + 1
		}
		StringBuffer newMrpNo = new StringBuffer();
		newMrpNo.append("RP");
		newMrpNo.append(mrpRegisterDate.replace("-", ""));
		newMrpNo.append("-");

		// 二쇱깮�궛怨꾪쉷踰덊샇瑜� �떞�쓣 HashSet : 以묐났�맂 踰덊샇�룄 �븯�굹留� �엯�젰�맖
		HashSet<String> mpsNoList = new HashSet<>();

		for (MrpTO bean : newMrpList) {

			bean.setMrpNo(newMrpNo.toString() + String.format("%03d", i++));
			// 3�옄由щ줈 �씪�젴踰덊샇 �몴�쁽�븯怨좎떢�쓣�븣 �궗�슜.
			bean.setStatus("INSERT");
			mpsNoList.add(bean.getMpsNo());

		}

		// �깉濡쒖슫 MRP 鍮덉쓣 batchListProcess 濡� �뀒�씠釉붿뿉 ���옣
		resultMap = batchMrpListProcess(newMrpList);

		// �깮�꽦�맂 MRP �씪�젴踰덊샇瑜� ���옣�븷 TreeSet
		TreeSet<String> mrpNoSet = new TreeSet<>();

		// "INSERT" 紐⑸줉�뿉 �깉濡� �깮�꽦�맂 MRP �씪�젴踰덊샇�뱾�씠 �엳�쓬, ArrayList 濡� �삎蹂��솚
		@SuppressWarnings("unchecked")
		ArrayList<String> mrpNoArr = (ArrayList<String>) resultMap.get("INSERT");

		for (String mrpNo : mrpNoArr) {
			mrpNoSet.add(mrpNo); // ArrayList �쓽 MRP �씪�젴踰덊샇�뱾�쓣 TreeSet �뿉 ���옣

		}

		resultMap.put("firstMrpNo", mrpNoSet.pollFirst()); // 理쒖큹 MRP �씪�젴踰덊샇瑜� 寃곌낵 Map �뿉 ���옣
		resultMap.put("lastMrpNo", mrpNoSet.pollLast()); // 留덉�留� MRP �씪�젴踰덊샇 寃곌낵 Map �뿉 ���옣

		// MPS �뀒�씠釉붿뿉�꽌 �빐�떦 mpsNo �쓽 MRP �쟻�슜�긽�깭瑜� "Y" 濡� 蹂�寃�
		for (String mpsNo : mpsNoList) {
			param = new HashMap<>();
			param.put("mpsNo", mpsNo);
			param.put("mrpStatus", "Y");
			mpsDAO.changeMrpApplyStatus(param);

		}

		// MRP �쟻�슜�긽�깭瑜� "Y" 濡� 蹂�寃쏀븳 二쇱깮�궛怨꾪쉷踰덊샇�뱾�쓣 寃곌낵 Map �뿉 ���옣
		resultMap.put("changeMrpApplyStatus", mpsNoList.toString().replace("[", "").replace("]", ""));

		return resultMap;
	}

	public HashMap<String, Object> batchMrpListProcess(ArrayList<MrpTO> mrpTOList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		for (MrpTO bean : mrpTOList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":

				// dao �뙆�듃 �떆�옉
				mrpDAO.insertMrp(bean);
				// dao �뙆�듃 �걹

				insertList.add(bean.getMrpNo());

				break;

			case "UPDATE":

				// dao �뙆�듃 �떆�옉
				mrpDAO.updateMrp(bean);
				// dao �뙆�듃 �걹

				updateList.add(bean.getMrpNo());

				break;

			case "DELETE":

				// dao �뙆�듃 �떆�옉
				mrpDAO.deleteMrp(bean);
				// dao �뙆�듃 �걹

				deleteList.add(bean.getMrpNo());

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;
	}

	public ArrayList<MrpGatheringTO> getMrpGathering(String mrpNoArr) {

		ArrayList<MrpGatheringTO> mrpGatheringList = null;

		String mrpNoList = mrpNoArr.toString().replace("[", "").replace("]", "");
		mrpGatheringList = mrpGatheringDAO.getMrpGathering(mrpNoList);

		return mrpGatheringList;

	}

	public HashMap<String, Object> registerMrpGathering(String mrpGatheringRegisterDate,
			ArrayList<MrpGatheringTO> newMrpGatheringList, HashMap<String, String> mrpNoAndItemCodeMap) {
		HashMap<String, Object> resultMap = null;
		HashMap<String, String> param = new HashMap<>();

		int seq = mrpGatheringDAO.selectMrpGatheringSeqCount();

		// �냼�슂�웾 痍⑦빀�씪�옄濡� �깉濡쒖슫 �냼�슂�웾 痍⑦빀踰덊샇 �솗�씤
		int i;
		ArrayList<MrpGatheringTO> list = mrpGatheringDAO.selectMrpGatheringCount(mrpGatheringRegisterDate);

		TreeSet<Integer> intSet = new TreeSet<>();

		for (MrpGatheringTO mrpGathering : list) {
			String mrpGatheringNo = mrpGathering.getMrpGatheringNo();

			// mrpGathering �씪�젴踰덊샇�뿉�꽌 留덉�留� 2�옄由щ쭔 媛��졇�삤湲�
			int no = Integer.parseInt(mrpGatheringNo.substring(mrpGatheringNo.length() - 2, mrpGatheringNo.length()));

			intSet.add(no);
		}

		if (intSet.isEmpty()) {
			i = 1;
		} else {
			i = intSet.pollLast() + 1; // 媛��옣 �넂�� 踰덊샇 + 1
		}
		/*
		 * ( itemCode : �깉濡쒖슫 mrpGathering �씪�젴踰덊샇 ) �궎/媛� Map => itemCode 濡� mrpNo ��
		 * mrpGatheringNo 瑜� 留ㅼ묶
		 */
		HashMap<String, String> itemCodeAndMrpGatheringNoMap = new HashMap<>();

		// �깉濡쒖슫 mrpGathering �씪�젴踰덊샇 �뼇�떇 �깮�꽦 : �벑濡앹씪�옄 '2018-01-01' => �씪�젴踰덊샇
		// 'MG20180101-'
		StringBuffer newMrpGatheringNo = new StringBuffer();
		newMrpGatheringNo.append("MG");
		newMrpGatheringNo.append(mrpGatheringRegisterDate.replace("-", ""));
		newMrpGatheringNo.append("-");

		// 새로운 mrpGathering 빈에 일련번호 입력 / status 를 "INSERT" 로 변경
		for (MrpGatheringTO bean : newMrpGatheringList) {
			bean.setMrpGatheringSeq(seq);
			bean.setMrpGatheringNo(newMrpGatheringNo.toString() + String.format("%03d", i++));
			bean.setStatus("INSERT");

			// mrpGathering 鍮덉쓽 itemCode �� mrpGatheringNo 瑜� map �뿉 ���옣
			itemCodeAndMrpGatheringNoMap.put(bean.getItemCode(), bean.getMrpGatheringNo());

		}

		// �깉濡쒖슫 mrpGathering 鍮덉쓣 batchListProcess 濡� �뀒�씠釉붿뿉 ���옣, 寃곌낵 Map 諛섑솚
		resultMap = batchMrpGatheringListProcess(newMrpGatheringList);

		// �깮�꽦�맂 mrp �씪�젴踰덊샇瑜� ���옣�븷 TreeSet
		TreeSet<String> mrpGatheringNoSet = new TreeSet<>();

		// "INSERT_LIST" 紐⑸줉�뿉 "itemCode - mrpGatheringNo" �궎/媛� Map �씠 �엳�쓬
		@SuppressWarnings("unchecked")
		HashMap<String, String> mrpGatheringNoList = (HashMap<String, String>) resultMap.get("INSERT_MAP");

		for (String mrpGatheringNo : mrpGatheringNoList.values()) {
			mrpGatheringNoSet.add(mrpGatheringNo); // ArrayList �쓽 mrpGathering �씪�젴踰덊샇�뱾�쓣 TreeSet �뿉 ���옣

		}

		resultMap.put("firstMrpGatheringNo", mrpGatheringNoSet.pollFirst()); // 理쒖큹 mrpGathering �씪�젴踰덊샇瑜� 寃곌낵 Map �뿉
																				// ���옣
		resultMap.put("lastMrpGatheringNo", mrpGatheringNoSet.pollLast()); // 留덉�留� mrpGathering �씪�젴踰덊샇瑜� 寃곌낵 Map �뿉
																			// ���옣

		// MRP �뀒�씠釉붿뿉�꽌 �빐�떦 mrpNo �쓽 mrpGatheringNo ���옣, �냼�슂�웾痍⑦빀 �쟻�슜�긽�깭瑜� "Y" 濡�
		// 蹂�寃�
		// itemCode 濡� mrpNo �� mrpGatheringNo 瑜� 留ㅼ묶�떆�궓�떎
		for (String mrpNo : mrpNoAndItemCodeMap.keySet()) {

			String itemCode = mrpNoAndItemCodeMap.get(mrpNo);
			String mrpGatheringNo = itemCodeAndMrpGatheringNoMap.get(itemCode);
			param.put("mrpNo", mrpNo);
			param.put("mrpGatheringNo", mrpGatheringNo);
			param.put("mrpGatheringStatus", "Y");
			mrpDAO.changeMrpGatheringStatus(param);
		}

		// MRP �쟻�슜�긽�깭瑜� "Y" 濡� 蹂�寃쏀븳 MRP 踰덊샇�뱾�쓣 寃곌낵 Map �뿉 ���옣
		resultMap.put("changeMrpGatheringStatus",
				mrpNoAndItemCodeMap.keySet().toString().replace("[", "").replace("]", ""));

		StringBuffer sb = new StringBuffer();

		for (String mrpGatheringNo : mrpGatheringNoList.values()) {
			sb.append(mrpGatheringNo);
			sb.append(",");
		}

		sb.delete(sb.toString().length() - 1, sb.toString().length());

		System.out.println("sb");
		System.out.println(sb.toString());

		HashMap<String, String> parameter = new HashMap<>();
		parameter.put("mrpGatheringNoList", sb.toString());
		mrpGatheringDAO.updateMrpGatheringContract(parameter);

		return resultMap;
	}

	public HashMap<String, Object> batchMrpGatheringListProcess(ArrayList<MrpGatheringTO> mrpGatheringTOList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		HashMap<String, String> insertListMap = new HashMap<>(); // "itemCode : mrpGatheringNo" �쓽 留�
		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		for (MrpGatheringTO bean : mrpGatheringTOList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":
				mrpGatheringDAO.insertMrpGathering(bean);

				insertList.add(bean.getMrpGatheringNo());

				insertListMap.put(bean.getItemCode(), bean.getMrpGatheringNo());

				break;

			case "UPDATE":

				mrpGatheringDAO.updateMrpGathering(bean);

				updateList.add(bean.getMrpGatheringNo());

				break;

			case "DELETE":

				mrpGatheringDAO.deleteMrpGathering(bean);

				deleteList.add(bean.getMrpGatheringNo());

				break;

			}

		}

		resultMap.put("INSERT_MAP", insertListMap);
		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;
	}

}
