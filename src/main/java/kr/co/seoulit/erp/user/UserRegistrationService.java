package kr.co.seoulit.erp.user;

import kr.co.seoulit.erp.config.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final UserFindService userFindService;


    public void requestRegistration(
        final String name,
        final String email
    ) {
        final boolean exists = userFindService.existsByEmail(email);
        if (exists == false) {
            final User user = new User(name, email);
            userRepository.save(user);
        }
    }

    public void requestRegistration(
        final String name,
        final String password,
        final String email
    ) {
        final boolean exists = userFindService.existsByEmail(email);
        if (exists == false) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String securePw = encoder.encode(password);
            final User user = new User(name, securePw, email);
            userRepository.save(user);
        }
    }
}
