package kr.co.seoulit.erp.config.oauth;

import kr.co.seoulit.erp.user.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

//@Service
@RequiredArgsConstructor
public class NaverOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRegistrationService userRegistrationService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2UserService<OAuth2UserRequest, OAuth2User> userService = new DefaultOAuth2UserService();
        final OAuth2User oAuth2User = userService.loadUser(userRequest);
        Map<String,Object> response = oAuth2User.getAttribute("response");
        String name = response.get("name").toString();
        final String email = response.get("email").toString();
        userRegistrationService.requestRegistration(name, email);

        return new DefaultOAuth2User(
            oAuth2User.getAuthorities(),
            response,
            "id"
        );
    }
}
