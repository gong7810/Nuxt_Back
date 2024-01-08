package kr.co.seoulit.erp.account.funds.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.co.seoulit.erp.account.funds.to.NoteBean;
import kr.co.seoulit.erp.account.funds.to.NoteSpecBean;
import kr.co.seoulit.erp.account.funds.to.NoteStatusBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.seoulit.erp.account.funds.servicefacade.NoteServiceFacade;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc/funds/")
public class NoteController {
    @Autowired
    private NoteServiceFacade noteServiceFacade;

    //GET 어음명세서 조회
    @GetMapping("/notes/{accountCode}")
    public ResponseEntity<Map<String,Object>> findNoteList(@PathVariable String accountCode) {
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<NoteBean> noteList = noteServiceFacade.findNoteList(accountCode);
        if(noteList.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        map.put("noteList", noteList);

        return ResponseEntity.ok(map);
    }

    //GET 어음현황 조회
    @GetMapping("/notes")
    public ResponseEntity<Map<String,Object>> fetchNoteStatus(@RequestParam("date") String date) {

        HashMap<String,Object> noteStatusMap = noteServiceFacade.fetchNoteStatus(date);
        if(noteStatusMap.size()==0){
            return ResponseEntity.notFound().build(); //없을경우
        }

        return ResponseEntity.ok(noteStatusMap);
    }
}
