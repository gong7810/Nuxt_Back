package kr.co.seoulit.erp.account.slip.repository;

import kr.co.seoulit.erp.account.slip.to.JournalDetailBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface JournalDetailRepository extends JpaRepository<JournalDetailBean,Integer> {

    ArrayList<JournalDetailBean> findByJournalNo(String journalNo);

    @Query("DELETE FROM JournalDetailBean journalDetailBean WHERE journalDetailBean.journalNo = :journalNo")
    void deleteByJournalNo(@Param("journalNo") String journalNo);
}
