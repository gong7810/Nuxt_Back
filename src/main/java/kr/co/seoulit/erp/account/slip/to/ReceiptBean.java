package kr.co.seoulit.erp.account.slip.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="RECEIPT")
public class ReceiptBean extends BaseTO{

    @Id
    private String slipNo;
    private String accountPeriodNo;
    private String reportingDate;
    private String expenseReport;
    private String receiptType;
    private String proofStatus;
}
