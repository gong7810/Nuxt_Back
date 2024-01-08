package kr.co.seoulit.erp.account.budget.applicationservice;

import kr.co.seoulit.erp.account.budget.entity.BudgetEntity;
import kr.co.seoulit.erp.account.budget.repository.JpaBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaBudgetServiceImpl implements JpaBudgetService {
    @Autowired
    JpaBudgetRepository jpaBudgetRepository;

    @Override
    public void save(BudgetEntity budgetEntity){
        jpaBudgetRepository.save(budgetEntity);
    }
}
