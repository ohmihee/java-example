package com.example.exampleboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfig {
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // 사용자의 인증정보를 메모리에서 관리
        // 간단한 어플리케이션이나 테스트 용도로 사용하는 경우에 메모리에서 사용자 인증정보를 관리하도록 하여
        // 별도의 데이터베이스 설정이나 외부 서비스 호출을 하지 않아도 된다.
        // 실제 프로덕션 환경에서 사용자 정보를 메모리에 저장하고 관리하는 것은 권장되지 않으며
        // 실제 데이터베이스나 외부 인증 서비스를 이용하는 것이 권장된다.
        manager.createUser(
                User.withUsername("user")
                        .password(bCryptPasswordEncoder.encode("userPass"))
                        .roles("USER")
                        .build()
        );
        manager.createUser(
                User.withUsername("admin")
                        .password(bCryptPasswordEncoder.encode("adminPass"))
                        .roles("ADMIN", "USER")
                        .build()
        );
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
