package kr.co.seoulit.erp.account.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@Data
public class AccountEntity {
    @Id
    private String accountInnerCode;
    private String parentAccountInnerCode;
    private String accountCode;
    private String accountCharacter;
    private String accountName;
    private String accountDisplayNameWithCode;
    private String accountDisplayName;
    private String accountDivision;
    private String accountUseCheck;
    private String accountDescription;
    private String groupCode;
    private String editable;
    private String target;
    private String budget;
    private String fixedAssets;
//    private ArrayList<AccountControlBean> accountControlList;
}
