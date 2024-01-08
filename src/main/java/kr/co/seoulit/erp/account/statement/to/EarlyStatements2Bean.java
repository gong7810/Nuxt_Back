package kr.co.seoulit.erp.account.statement.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class EarlyStatements2Bean extends BaseTO{

    private long lev;
    private long debitsSumBalance;
    private long debitsSum;
    private long creditsSumBalance;
    private long creditsSum;
    private String code;
    private String accountPeriodNo;
    private String accountName;

}


