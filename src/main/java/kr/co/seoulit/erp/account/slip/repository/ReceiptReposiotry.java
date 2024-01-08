package kr.co.seoulit.erp.account.slip.repository;

import kr.co.seoulit.erp.account.slip.to.ReceiptBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
public interface ReceiptReposiotry extends JpaRepository<ReceiptBean,String> {
    @Query("select receipt from ReceiptBean receipt order by receipt.slipNo asc")
    ArrayList<ReceiptBean> findAllByOrderBySlipNo();

    @Query("select receipt from ReceiptBean receipt where receipt.slipNo = :slipNo")
    ReceiptBean findBySlipNo(@Param("slipNo") String slipNo);
}
