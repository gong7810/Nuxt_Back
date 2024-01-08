package kr.co.seoulit.erp.account.slip.to;

import lombok.Data;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class JournalDoubleBean {
	private String reportingDate;
	private String leftAccountName;
	private String leftDebitPrice;
	private String rightAccountName;
	private String rightCreditPrice;
	private String customerName;
	private String expenseReport;
}
