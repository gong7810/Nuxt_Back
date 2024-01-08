package kr.co.seoulit.erp.account.slip.repository;


import kr.co.seoulit.erp.account.slip.to.JournalBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JournalRepository extends JpaRepository<JournalBean,String> {

    @Query("DELETE FROM JournalBean journalBean WHERE journalBean.slipNo = :slipNo")
    void deleteBySlipNo(@Param("slipNo") String slipNo);
}
