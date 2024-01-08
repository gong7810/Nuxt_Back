package kr.co.seoulit.erp.account.budget.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BudgetProductPK implements Serializable {
    private String accountInnerCode;
    private String accountPeriodNo;
    private String budgetingCode;
    private String deptCode;
    private String workplaceCode;
}
