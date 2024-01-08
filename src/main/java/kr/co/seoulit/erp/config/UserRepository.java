package kr.co.seoulit.erp.config;

import kr.co.seoulit.erp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    User findByUsername(String username);
}
