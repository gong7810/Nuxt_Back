package kr.co.seoulit.erp.config.oauth.provider;

import java.util.LinkedHashMap;
import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{

    private Map<String,Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    @Override
    public String getProviderId() {
        return this.attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
//        LinkedHashMap<String, Object> kakaoAccount = (LinkedHashMap<String, Object>) this.attributes.get("kakao_account");
//        String email = kakaoAccount.get("email").toString();

        return "gong5518@gmail.com";
    }

    @Override
    public String getName() {
        LinkedHashMap<String, Object> kakaoAccount = (LinkedHashMap<String, Object>) this.attributes.get("kakao_account");
        LinkedHashMap<String, Object> profile = (LinkedHashMap<String, Object>) kakaoAccount.get("profile");
        String name = profile.get("nickname").toString();

        return name;
    }
}