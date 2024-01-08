package kr.co.seoulit.erp.account.budget.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="BUDGET")
@Data
@IdClass(BudgetProductPK.class)
public class BudgetEntity implements Serializable {
    @Id
    private String accountInnerCode;
    @Id
    private String accountPeriodNo;
    @Id
    private String budgetingCode;
    @Id
    private String deptCode;
    @Id
    private String workplaceCode;
    @Column(name = "M1_BUDGET")
    private String m1Budget;
    @Column(name = "M2_BUDGET")
    private String m2Budget;
    @Column(name = "M3_BUDGET")
    private String m3Budget;
    @Column(name = "M4_BUDGET")
    private String m4Budget;
    @Column(name = "M5_BUDGET")
    private String m5Budget;
    @Column(name = "M6_BUDGET")
    private String m6Budget;
    @Column(name = "M7_BUDGET")
    private String m7Budget;
    @Column(name = "M8_BUDGET")
    private String m8Budget;
    @Column(name = "M9_BUDGET")
    private String m9Budget;
    @Column(name = "M10_BUDGET")
    private String m10Budget;
    @Column(name = "M11_BUDGET")
    private String m11Budget;
    @Column(name = "M12_BUDGET")
    private String m12Budget;

}
