package kr.co.seoulit.erp.account.statement.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class EarlyStatementsBean extends BaseTO {
    private String earlyStatementsNo;
    private int accountPeriodNo;
    private String accountInnerCode;
    private int leftDebitPrice;
    private int rightCreditPrice;
    private int lev;
    private int debitsSum;
    private int creditsSum;

}
