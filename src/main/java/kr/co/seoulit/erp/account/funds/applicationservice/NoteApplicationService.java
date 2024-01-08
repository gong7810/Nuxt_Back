package kr.co.seoulit.erp.account.funds.applicationservice;

import kr.co.seoulit.erp.account.funds.to.NoteBean;

import java.util.ArrayList;
import java.util.HashMap;

public interface NoteApplicationService {

    // 어음명세서 조회
    ArrayList<NoteBean> findNoteList(String accountCode);

    // 어음현황 조회
    HashMap<String,Object> fetchNoteStatus(String date);
}
