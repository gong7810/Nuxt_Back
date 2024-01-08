package kr.co.seoulit.erp.account.slip.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import kr.co.seoulit.common.to.BaseTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="JOURNAL")
public class JournalBean extends BaseTO{

    @Id
    private String journalNo;
    private String slipNo;
    private String balanceDivision;
    private String accountInnerCode;
    private String customerCode;
    private String leftDebitPrice;
    private String rightCreditPrice;
    private String accountName;
    @Transient
    private String Price;
    @Transient
    private String customerName;
    @Transient
    private String accountCode;
    @Transient
    private List<JournalDetailBean> JournalDetailList;

}
