package kr.co.seoulit.erp.account.funds.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;

import lombok.EqualsAndHashCode;

import jakarta.persistence.Id;

@EqualsAndHashCode(callSuper = false)
@Data
public class NoteSpecBean extends BaseTO {
    @Id
    private String noteNo;
    private String customerCode;
    private String customerName;
    private String notePrice;
    private String maturityDate;
    private String showDetails;
}
