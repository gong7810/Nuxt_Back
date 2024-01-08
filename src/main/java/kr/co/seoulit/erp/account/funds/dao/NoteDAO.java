package kr.co.seoulit.erp.account.funds.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.funds.to.NoteBean;
import kr.co.seoulit.erp.account.funds.to.NoteStatusBean;
import org.apache.ibatis.annotations.Mapper;
import kr.co.seoulit.erp.account.funds.to.NoteSpecBean;

@Mapper
public interface NoteDAO {

    // 어음명세서 조회
    public ArrayList<NoteBean> findNoteList(String accountCode);

    // 어음 한개조회
    public NoteBean findNoteByJournalNo(String journalNo);

    // 어음상세 삭제
    public void delelteNote(String journalNo);

    // 어음상세 수정
    public void updateNote(NoteBean noteBean);

    // 어음현황 조회
    public HashMap<String, Object> fetchNoteStatus(HashMap<String, Object> map);
}
