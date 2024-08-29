package com.sdw.springsecuriy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // URL 접근 제어 설정
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/login", "/join", "/joinPage").permitAll()  // 특정 경로에 대한 접근 허용
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("/ADMIN", "USER")
                        .anyRequest().authenticated()  // 나머지 모든 요청에 대해 인증 요구
                )
                // 로그인 설정
                .formLogin((form) -> form
                        .loginPage("/login")  // 커스텀 로그인 페이지 경로 설정
                        .loginProcessingUrl("/loginPage")
                        .permitAll()  // 로그인 페이지 접근 허용
                )
                // 로그아웃 설정
                .logout((logout) -> logout
                        .permitAll()  // 로그아웃은 모든 사용자에게 허용
                )
                // csrf
                .csrf((csrf) -> csrf.disable());

        return http.build();
    }

    // 인메모리 사용자 세부정보 관리 설정
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")  // 사용자 이름
                .password("password")  // 비밀번호
                .roles("USER")  // 역할
                .build());
        return manager;
    }
}
