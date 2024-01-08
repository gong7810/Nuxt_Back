package kr.co.seoulit.erp.account.statement.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import kr.co.seoulit.common.to.BaseTO;

@EqualsAndHashCode(callSuper = false)
@Data
public class MonthCostStatementBean extends BaseTO {

    private String accountName;
    private String costSummary;
    private String january;
    private String february;
    private String march;
    private String april;
    private String may;
    private String june;
    private String july;
    private String august;
    private String september;
    private String october;
    private String november;
    private String december;
}
