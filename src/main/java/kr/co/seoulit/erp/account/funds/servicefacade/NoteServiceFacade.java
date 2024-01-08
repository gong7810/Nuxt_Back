package kr.co.seoulit.erp.account.funds.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.funds.to.*;

public interface NoteServiceFacade {

    // 어음명세서 조회
    ArrayList<NoteBean> findNoteList(String accountCode);

    // 어음현황 조회
    HashMap<String,Object> fetchNoteStatus(String date);
}
