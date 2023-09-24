package com.cosight.ssk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    // private final PasswordEncoder passwordEncoder;
    // private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // SEE: https://stackoverflow.com/questions/73089730/authorizerequests-vs-authorizehttprequestscustomizerauthorizehttprequestsc
        http.authorizeHttpRequests()
            .antMatchers("/", "/login", "/signup").permitAll()
            .anyRequest().authenticated();

        http.csrf().disable();

        // http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 커스텀 로그인 페이지를 사용하기 위함
        // http.formLogin().disable()
        //     .logout().disable();

        // http.exceptionHandling()
        //     .accessDeniedPage("/errors/403");

        http.formLogin()
            .loginPage("/login")            // 사용자 정의 로그인 페이지
            // .defaultSuccessUrl("/")            // 로그인 성공 후 이동 페이지
            // .failureUrl("/login.html?error=true")            // 로그인 실패 후 이동 페이지
            .usernameParameter("username")            // 아이디 파라미터명 설정
            .passwordParameter("password")            // 패스워드 파라미터명 설정
            .loginProcessingUrl("/login");            // 로그인 Form Action Url
        // .successHandler(this.loginSuccessHandler())        // 로그인 성공 후 핸들러
        // .failureHandler(this.loginFailureHandler());        // 로그인 실패 후 핸들러

        return http.build();
    }


    // SEE: https://stackoverflow.com/questions/71605941/spring-security-global-authenticationmanager-without-the-websecurityconfigurera
    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    //     return configuration.getAuthenticationManager();
    // }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder securityBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        securityBuilder.userDetailsService(userDetailsService)
                       .passwordEncoder(this.passwordEncoder());

        return securityBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
