package kr.co.seoulit.erp.account.statement.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class FinancialPositionBean extends BaseTO{
    private long lev;  //int -> long으로 수정
    private String category;
    private String accountName;
    private String accountCode;
    private long balanceDetail; //int -> long으로 수정
    private long balanceSummary;
    private long preBalanceDetail;   //int -> long으로 수정
    private long preBalanceSummary;  //int -> long으로 수정
    private long isThisYear; //int -> long으로 수정

}
