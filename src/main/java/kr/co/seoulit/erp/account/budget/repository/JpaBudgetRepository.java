package kr.co.seoulit.erp.account.budget.repository;

import kr.co.seoulit.erp.account.budget.entity.BudgetEntity;
import kr.co.seoulit.erp.account.budget.entity.BudgetProductPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBudgetRepository extends CrudRepository<BudgetEntity, BudgetProductPK> {
}
