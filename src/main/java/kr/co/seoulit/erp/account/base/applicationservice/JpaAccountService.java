package kr.co.seoulit.erp.account.base.applicationservice;

import kr.co.seoulit.erp.account.base.entity.AccountEntity;

import java.util.ArrayList;

public interface JpaAccountService {
    public ArrayList<AccountEntity> findParentAccountList(String accountInnerCode, ArrayList<String> parentAccountInnerCode);
}
