package kr.co.seoulit.erp.account.base.to;

import kr.co.seoulit.common.to.*;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ACCOUNT_CONTROL_DETAIL")
public class AccountControlBean extends BaseTO{
    @Id
    private String accountControlCode;
    private String accountControlName;
    private String accountControlType;

}
