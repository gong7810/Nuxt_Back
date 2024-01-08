package kr.co.seoulit.erp.account.base.repository;

import kr.co.seoulit.erp.account.base.entity.PeriodEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface JpaPeriodRepository extends CrudRepository<PeriodEntity, String> {
    ArrayList<PeriodEntity> findAll();
}
