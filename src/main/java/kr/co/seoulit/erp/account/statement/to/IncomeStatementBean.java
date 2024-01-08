package kr.co.seoulit.erp.account.statement.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class IncomeStatementBean extends BaseTO{
    private String accountInnerCode;
    private String accountName;
    private String parentAccountCode;
    private long income;  //String -> long으로 수정
    private long incomeSummary;   //String -> long으로 수정
    private long earlyIncome; //String -> long으로 수정
    private long earlyIncomeSummary;  //String -> long으로 수정
    private String isThisYear;
    private long lev;   //  추가
    private String periodNo;    //  추가
}
