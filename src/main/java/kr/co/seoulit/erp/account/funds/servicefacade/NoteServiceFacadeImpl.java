package kr.co.seoulit.erp.account.funds.servicefacade;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.erp.account.funds.applicationservice.NoteApplicationService;
import kr.co.seoulit.erp.account.funds.to.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class NoteServiceFacadeImpl implements NoteServiceFacade{

    @Autowired
    private NoteApplicationService noteApplicationService;

    // 어음명세서 조회
    @Override
    public ArrayList<NoteBean> findNoteList(String accountCode) {
        return noteApplicationService.findNoteList(accountCode);
    }

    // 어음현황 조회
    @Override
    public HashMap<String,Object> fetchNoteStatus(String date) {
        return noteApplicationService.fetchNoteStatus(date);
    }
}
