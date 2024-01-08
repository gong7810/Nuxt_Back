package kr.co.seoulit.erp.account.funds.to;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="DAILY_FUND_PLAN")
public class PlanBean extends BaseTO {

    @Id
    private String planNo;
    private String planDate;
    private String fundCode;
    private String fundName;
    private String customerCode;
    private String customerName;
    private String expenseReport;
    private String balanceDivision;
    private String price;
}
