package kr.co.seoulit.erp.account.funds.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class DailyTradeStatusBean extends BaseTO {

    private String accountName;
    private String previousBalance;
    private String dayIncrease;
    private String dayDecrease;
    private String dayBalance;
}
