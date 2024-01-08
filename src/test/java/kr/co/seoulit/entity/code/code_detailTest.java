package kr.co.seoulit.entity.code;


import kr.co.seoulit.acc.entity.business.Business;
import kr.co.seoulit.acc.entity.business.Detail_business;
import kr.co.seoulit.acc.entity.code.Code;
import kr.co.seoulit.acc.entity.code.Code_detail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class code_detailTest {


    @PersistenceContext
    private EntityManager em;

    @Test
    public void code_detail() throws Exception {
    //given
        Code_detail code_detail = new Code_detail();

        Code code = new Code();
        em.persist(code);

        code_detail.setCode(code);
        code_detail.setDescription("test");


        em.persist(code_detail);



    }
    
    @Test
    public void business() throws Exception {
        Business business = new Business();
        em.persist(business);
        
        Detail_business detail_business = new Detail_business();

        detail_business.setId("A");
        
        detail_business.setBusiness(business);
        
        em.persist(detail_business);

        System.out.println("detail_business.getId() = " + detail_business.getId());
        

    }


}