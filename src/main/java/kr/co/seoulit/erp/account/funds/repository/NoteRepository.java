package kr.co.seoulit.erp.account.funds.repository;

import kr.co.seoulit.erp.account.funds.to.NoteBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteBean,String> {
}
