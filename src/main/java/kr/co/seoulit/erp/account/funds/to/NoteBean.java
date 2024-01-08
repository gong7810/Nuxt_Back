package kr.co.seoulit.erp.account.funds.to;

import lombok.Data;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

import kr.co.seoulit.common.to.BaseTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="NOTE")
public class NoteBean extends BaseTO{

    @Id
    private String noteNo;
    private String journalNo;
    private String noteType;
    private String accountInnerCode;
    private String drawer;
    private String endorser;
    private String drawee;
    private String issuanceDate;
    private String maturityDate;
    @Transient
    private String slipNo;
    @Transient
    private String approvalEmpCode;
    @Transient
    private String expenseReport;
    @Transient
    private String customerName;
    @Transient
    private String customerCode;
    @Transient
    private String notePrice;
    @Transient
    private String id;
}
