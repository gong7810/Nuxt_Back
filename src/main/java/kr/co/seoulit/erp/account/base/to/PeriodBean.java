package kr.co.seoulit.erp.account.base.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import kr.co.seoulit.common.to.BaseTO;

@EqualsAndHashCode(callSuper=false)
@Data
public class PeriodBean extends BaseTO{

	private String accountPeriodNo; //String에서 Integer로 변경 , Integer에서 String으로 다시 변경
	private String fiscalYear;
	private String workplaceCode;
	private String periodStartDate;
	private String periodEndDate;

}
