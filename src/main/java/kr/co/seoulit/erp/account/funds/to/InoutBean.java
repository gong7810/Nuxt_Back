package kr.co.seoulit.erp.account.funds.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class InoutBean extends BaseTO {

    private String accountName;
    private String expenseReport;
    private String price;

}
