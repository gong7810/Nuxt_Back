package kr.co.seoulit.erp.account.base.repository;

import kr.co.seoulit.erp.account.base.entity.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface JpaAccountRepository extends CrudRepository<AccountEntity, String> {
    ArrayList<AccountEntity> findByAccountInnerCodeLikeAndParentAccountInnerCodeNotInOrderByAccountInnerCode(String accountInnerCode, ArrayList<String> parentAccountInnerCode);

    // JPQL이라 사용하지 않음.
    @Query(value = "SELECT b.accountInnerCode as accountInnerCode," +
            " b.accountName as accountName1, " +
            " b.parentAccountInnerCode as parentAccountInnercode," +
            " b.groupCode AS groupCode, " +
            " b.accountCode AS accountCode," +
            " a.accountName as accountName2 " +
            "FROM AccountEntity a, AccountEntity b WHERE b.accountInnerCode NOT LIKE '%-%' AND b.parentAccountInnerCode = :code AND a.accountInnerCode = b.parentAccountInnerCode ORDER BY b.accountInnerCode")
    ArrayList<AccountEntity> findByAccountInnerCodeLikeAndParentAccountInnerCodeNotInOrderByAccountInnerCode(String code);
}
