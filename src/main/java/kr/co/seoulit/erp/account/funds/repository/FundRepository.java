package kr.co.seoulit.erp.account.funds.repository;

import kr.co.seoulit.erp.account.funds.to.PlanBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<PlanBean,String> {

}
