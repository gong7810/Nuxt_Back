package kr.co.seoulit.erp.account.base.applicationservice;

import kr.co.seoulit.erp.account.base.entity.AccountEntity;
import kr.co.seoulit.erp.account.base.repository.JpaAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JpaAccountSerivceImpl implements JpaAccountService{
    @Autowired
    JpaAccountRepository accountRepository;

    @Override
    public ArrayList<AccountEntity> findParentAccountList(String accountInnerCode, ArrayList<String> parentAccountInnerCode) {
        ArrayList<AccountEntity> account = accountRepository.findByAccountInnerCodeLikeAndParentAccountInnerCodeNotInOrderByAccountInnerCode(accountInnerCode, parentAccountInnerCode);
        System.out.println("account넘어와."+account);
        return account;
    }
}
