package com.example.exampleboot.config;

import com.example.exampleboot.security.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // HttpSecurity 객체를 받아서 보안 설정
        // HttpSecurity -> Sprint Security의 구성 클래스
        http.csrf(AbstractHttpConfigurer::disable)
                // CSRF(Crostt-Site Request Forgery) 보호를 비활성화한다. 주로 RestFul API 에서 주로 사용
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        // 요청에 대한 인가 규칙을 정의한다.
                        authorizationManagerRequestMatcherRegistry.requestMatchers("/**")
                                // /** -> 모든 요청에 대해 인증된 사용자에게만 허용
                                .authenticated()
                        ).httpBasic(Customizer.withDefaults())
                // HTTP 기본 인증을 설정한 것으로, 서버 요청 시에 기본 인증 헤더를 함께 제공해야 한다.
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        // 세션 관리 설정 관련
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // SessionCreationPolicy.STATELESS -> 각 요청에 완전히 독립적으로 작용한다.
                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // 사용자 지정 인증 필터를 추가한 것으로, 이는 UsernamePasswordAuthenticationFilter  이전에 적용된다.
        return http.build();
    }
}
