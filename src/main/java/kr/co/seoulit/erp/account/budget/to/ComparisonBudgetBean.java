package kr.co.seoulit.erp.account.budget.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ComparisonBudgetBean extends BaseTO {
    private String budgetDate;
    private String appBudget;
    private String orgBudget;
    private String execPerform;
    private String budgetAccountComparison;

}
