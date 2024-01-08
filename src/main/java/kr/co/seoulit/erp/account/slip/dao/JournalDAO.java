package kr.co.seoulit.erp.account.slip.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.account.slip.to.GeneralAccountLedgerBean;
import kr.co.seoulit.erp.account.slip.to.JournalBean;
import kr.co.seoulit.erp.account.slip.to.JournalDoubleBean;
import kr.co.seoulit.erp.account.slip.to.JournalSeparatorBean;

@Mapper
public interface JournalDAO {

	public ArrayList<JournalBean> selectRangedJournalList(HashMap<String, String> map);

	public ArrayList<JournalBean> selectJournalList(String slipNo);

	public JournalBean selectJournal(String journalNo);

	public void insertJournal(JournalBean journalBean);

	public String selectJournalName(String slipNo);

	public boolean updateJournal(JournalBean journalBean);

	public void deleteJournal(String journalNo);

	public ArrayList<GeneralAccountLedgerBean> selectGeneralAccountLedgerList(HashMap<String, String> map);

	public ArrayList<JournalDoubleBean> selectJournalDoubleList(HashMap<String, String> map);

	public void insertJournalSeparator(HashMap<String, String> map);

	public ArrayList<JournalSeparatorBean> selectJournalSeparator(String SlipNo);

}
