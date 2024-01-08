package kr.co.seoulit.erp.account.funds.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class GeneralFundBean {

    private String indexNo;
    private String accountName;
    private String customerName;
    private String previousBalance;
    private String dayInout;
    private String dayBalance;
}
