package kr.co.seoulit.erp.config.oauth;

import kr.co.seoulit.erp.config.UserRepository;
import kr.co.seoulit.erp.config.oauth.provider.GoogleUserInfo;
import kr.co.seoulit.erp.config.oauth.provider.KakaoUserInfo;
import kr.co.seoulit.erp.config.oauth.provider.NaverUserInfo;
import kr.co.seoulit.erp.config.oauth.provider.OAuth2UserInfo;
import kr.co.seoulit.erp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    //구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;
        NaverUserInfo naverUserInfo = null;
        KakaoUserInfo kakaoUserInfo = null;
        String flag = null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
            flag = "google";
        }
        else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            naverUserInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
            flag = "naver";
        }
        else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            kakaoUserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            flag = "kakao";
        }
        else {
            System.out.println("구글과 네이버, 카카오 로그인만 지원합니다");
        }


        //회원가입을 강제로 진행
        if (flag == "google") {
            String provider = oAuth2UserInfo.getProvider(); //google
            String providerId = oAuth2UserInfo.getProviderId();
            String username = provider+ "_" + providerId; // google_107473074088454503127
            String password = bCryptPasswordEncoder.encode("seoulitboss"); //oauth2 로그인을 하면 비밀번호는 필요없지만 테이블에 null로 둘 수 없으니 임의의 비밀번호를 줌
            String email = oAuth2UserInfo.getEmail();
            String role = "ROLE_USER";

            User userEntity = userRepository.findByUsername(username);

            if(userEntity == null){
                userEntity = User.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .role(role)
                        .provider(provider)
                        .providerId(providerId)
                        .build();
                userRepository.save(userEntity);
            } else {
                System.out.println("소셜 로그인을 이미 한적이 있습니다.");
            }


            return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
        }
        else if (flag == "naver") {
            String provider = naverUserInfo.getProvider(); //naver
            String providerId = naverUserInfo.getProviderId();
            String username = provider+ "_" + providerId; // naver_1237123187238
            String password = bCryptPasswordEncoder.encode("seoulitboss"); //oauth2 로그인을 하면 비밀번호는 필요없지만 테이블에 null로 둘 수 없으니 임의의 비밀번호를 줌
            String email = naverUserInfo.getEmail();
            String role = "ROLE_USER";

            User userEntity = userRepository.findByUsername(username);

            if(userEntity == null){
                userEntity = User.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .role(role)
                        .provider(provider)
                        .providerId(providerId)
                        .build();
                userRepository.save(userEntity);
            } else {
                System.out.println("소셜 로그인을 이미 한적이 있습니다.");
            }


            return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
        }
        else if (flag == "kakao") {
            String provider = kakaoUserInfo.getProvider(); //kakao
            String providerId = kakaoUserInfo.getProviderId();
            String username = provider+ "_" + providerId; // kakao_3219614008
            String password = bCryptPasswordEncoder.encode("seoulitboss"); //oauth2 로그인을 하면 비밀번호는 필요없지만 테이블에 null로 둘 수 없으니 임의의 비밀번호를 줌
            String email = kakaoUserInfo.getEmail();
            String role = "ROLE_USER";

            User userEntity = userRepository.findByUsername(username);

            if(userEntity == null){
                userEntity = User.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .role(role)
                        .provider(provider)
                        .providerId(providerId)
                        .build();
                userRepository.save(userEntity);
            } else {
                System.out.println("소셜 로그인을 이미 한적이 있습니다.");
            }


            return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
        }
        else {
            return null;
        }
    }
}