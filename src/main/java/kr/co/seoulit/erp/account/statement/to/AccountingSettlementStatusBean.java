package kr.co.seoulit.erp.account.statement.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class AccountingSettlementStatusBean extends BaseTO {

    private String accountPeriodNo;
    private String totalTrialBalance;
    private String incomeStatement;
    private String financialPosition;
    private String capitalStatement;

}
