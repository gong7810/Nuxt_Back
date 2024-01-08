package kr.co.seoulit.erp.account.slip.to;

import java.util.ArrayList;

import kr.co.seoulit.common.to.BaseTO;
import kr.co.seoulit.erp.account.funds.to.NoteBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="SLIP")
public class SlipBean extends BaseTO{

    @Id
    private String slipNo;
    private String accountPeriodNo;
    private String deptCode;
    private String slipType;
    private String expenseReport;
    private String authorizationStatus;
    private String postingDate;
    private String reportingEmpCode;
    private String reportingDate;
    private String approvalEmpCode;
    private String approvalDate;
    private String slipDescription;
    private String slipStatus;
    private String reportingEmpName;

    @Transient
    private String balanceDivision;
    @Transient
    private String positionCode;
    @Transient
    private String approvalEmpName;
    @Transient
    private String deptName;
    @Transient
    private ArrayList<JournalBean> journalList;
    @Transient
    private ArrayList<JournalDeadlineDivsionBean> journalDeadline;
    @Transient
    private ArrayList<NoteBean> noteDetail;
}
