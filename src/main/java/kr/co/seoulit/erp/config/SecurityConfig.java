package kr.co.seoulit.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true) // secured 어노테이션 활성화
public class SecurityConfig {

    // 1.코드받기(인증)
    // 2. 엑세스토큰(권한)
    // 3.사용자프로필 정보를 가져오고
    // 4-1.그 정보를 토대로 회원가입을 자동으로 진행시키기도 함
    // 4-2(이메일,전화번호,이름,아이디) 쇼핑몰의 경우 추가적으로 집 주소가 필요함

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/manager/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/jpa/**").authenticated()
                .requestMatchers("/board/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("http://localhost:3000/")
                .and()
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/loginForm")
                        .defaultSuccessUrl("http://localhost:3000/")
                );
        return http.build();
    }
}