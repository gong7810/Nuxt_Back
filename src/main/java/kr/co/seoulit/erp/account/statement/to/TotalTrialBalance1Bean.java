package kr.co.seoulit.erp.account.statement.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class TotalTrialBalance1Bean extends BaseTO{

    private String accountPeriodNo;
    private Long lev;
    private String accountName;
    private String code;  //Code와 매칭!
    private Long debitsSumBalance;
    private Long debitsSum;
    private Long creditsSum;
    private Long creditsSumBalance;

}
