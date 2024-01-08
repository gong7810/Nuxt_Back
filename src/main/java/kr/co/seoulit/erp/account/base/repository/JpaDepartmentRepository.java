package kr.co.seoulit.erp.account.base.repository;

import kr.co.seoulit.erp.account.base.entity.DepartmentEntity;
import kr.co.seoulit.erp.account.base.entity.DepartmentPK;
import kr.co.seoulit.erp.account.base.entity.DepartmentSelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface JpaDepartmentRepository extends JpaRepository<DepartmentEntity, DepartmentPK> {
    @Query( value = "select distinct d.workplaceCode as workplaceCode, d.workplaceName as workplaceName from DepartmentEntity d order by d.workplaceCode")
    List<DepartmentSelectList> findDistinctWorkplaceCode();

    ArrayList<DepartmentEntity> findByWorkplaceCode(String workplaceCode);


}
