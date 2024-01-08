package kr.co.seoulit.erp.account.statement.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import kr.co.seoulit.common.to.BaseTO;

@EqualsAndHashCode(callSuper = false)
@Data
public class RetainedEarningsStatementBean extends BaseTO {

    private String currentYear;
    private String retained;
    private String retainedSummary;
    private String accountName;
    private String fiscalYear;
    private String preRetained;
    private String preRetainedSummary;
}
