package kr.co.seoulit.erp.account.funds.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class FinanceBean {

    private String customerCode;
    private String customerName;
    private String accountNo;
    private String previousBalance;
    private String increase;
    private String decrease;
    private String dayBalance;
    private String limitBalance;
}
