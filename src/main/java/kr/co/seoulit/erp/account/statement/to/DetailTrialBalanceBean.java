package kr.co.seoulit.erp.account.statement.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class DetailTrialBalanceBean extends BaseTO{
	private int lev;
	private String accountInnerCode;
	private String debitsSum;
	private int exceptCashDebits;
	private String cashDebits;
	private String accountName;
	private String cashCredits;
	private int exceptCashCredits;
	private String creditsSum;

}
