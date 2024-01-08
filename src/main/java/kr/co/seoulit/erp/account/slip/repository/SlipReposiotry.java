package kr.co.seoulit.erp.account.slip.repository;

import kr.co.seoulit.erp.account.slip.to.SlipBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface SlipReposiotry extends JpaRepository<SlipBean,String> {
    @Query("select slip from SlipBean slip order by slip.slipNo asc")
    ArrayList<SlipBean> findAllByOrderBySlipNo();
}
