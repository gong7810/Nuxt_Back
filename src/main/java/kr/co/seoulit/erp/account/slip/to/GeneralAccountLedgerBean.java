package kr.co.seoulit.erp.account.slip.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class GeneralAccountLedgerBean {
    private String reportingDate;
    private String accountName;
    private String leftDebitPrice;
    private String rightCreditPrice;
    private String customerName;
    private String expenseReport;

}
