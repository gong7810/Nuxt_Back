package kr.co.seoulit.erp.account.base.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
 
import kr.co.seoulit.common.to.BaseTO;


@EqualsAndHashCode(callSuper=false)
@Data
//=====================================  2020-08-25 계정별 원장    ====================================
public class LedgerByAccountBean extends BaseTO{
	private String carryForward;
    private String reportingDate;      //APPROVAL_DATE                            //전표 -> 분개 등록한 날짜
	private String accountName;   // 계정이름
	private String customerName;   // 거래처이름
	private String leftDebitPrice; //차변
	private String rightCreditPrice; //대변
	private String totalPrice; //잔액
	private String expenseReport;
	private String customerCode;

}
