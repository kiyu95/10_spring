package com.ohgiraffers.securitysession.config;

import com.ohgiraffers.securitysession.common.UserRole;
import com.ohgiraffers.securitysession.config.handler.AuthFailHandler;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthFailHandler authFailHandler;

    /*
    * 비밀번호를 인코딩 하기 위한 Bean
    * Bcrypt는 비밀번호 해싱에 가장 많이 사용되는 알고리즘 중 하나이다.
    *
    * 사용이유
    * 1. 보안성 : 해시 함수에 무작위 솔트를 적용하여 생성한다.
    * 2. 비용증가 : 매개변수에 값을 주면 암호 생성 시간을 조절할 수 있어 무차별 공격을 어렵게 한다.
    * 3. 호환성 : 높은 보완 수준 및 데이터베이스에 저장하기 쉬운 특징
    * 4. 알고리즘 신뢰성 : 보안에 논의 평가를 거친 알고리즘으로 문제 없이 계속 사용중
    * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean // 정적리소스에 대한 요청을 제외하겠다는 설정. (static 파일 하위)
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 필터 체인 설정
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> { // 서버의 리소스에 접근 가능한 권한을 설정함
            auth.requestMatchers("/auth/login", "/user/signup", "/auth/fail", "/").permitAll(); // 이 요청은 모든 사용자에게 허용
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole()); // 이 요청은 admin만 허용
            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole()); // 이 요청은 user만 허용
            auth.anyRequest().authenticated(); // 인증이 된 모든 사용자가 사용 가능
        }).formLogin(login ->{
            login.loginPage("/auth/login"); // 로그인 페이지에 해당되는 서블릿이 존재하야 한다.
            login.usernameParameter("user"); // input name값 변경
            login.passwordParameter("pass"); // input password값 변경
            login.defaultSuccessUrl("/"); // 로그인이 성공했을 때. 서블릿이 존재해야 한다.
            login.failureHandler(authFailHandler); // 로그인이 실패했을 때
        }).logout(logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout")); // 로그아웃 요청할 url
            logout.deleteCookies("JSESSIONID"); // 로그아웃시 쿠키 삭제
            logout.invalidateHttpSession(true); // 세션을 소멸하도록 허용하는 것
            logout.logoutSuccessUrl("/"); // 로그아웃시 이동할 페이지 설정
        }).sessionManagement(session -> {
            session.maximumSessions(1); // session의 허용 개수를 제한
            session.invalidSessionUrl("/"); // 세션 만료시 이동할 페이지
        }).csrf(csrf -> csrf.disable());

        return http.build();
    }
}
