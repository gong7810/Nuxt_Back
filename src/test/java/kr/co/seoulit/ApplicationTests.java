package kr.co.seoulit;

import kr.co.seoulit.acc.entity.menu.Slip;
import kr.co.seoulit.acc.slip.repository.SlipRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class ApplicationTests {

	@Autowired
	SlipRepository slipRepository;

	@Autowired
	EntityManager em;

	@Test
	public void testSlip() {

		Slip slip = em.find(Slip.class, "20220910SLIP00002");
		System.out.println("slip = " + slip);
	}

}
