package com.me.notify.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/user/login", "/user/join",
                                "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                );

        http
                .securityContext((context) -> context.requireExplicitSave(false))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        http.
                csrf((csrf) -> csrf
                        .csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/user/join")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        http
                .httpBasic(auth -> auth.disable())
                .formLogin(auth -> auth
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login").permitAll()
                )
                .logout(auth -> auth
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/user/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    /*
    이전에는 스프링 시큐리티의 인증을 담당하는 AuthenticationManager에
    authenticationManagerBuilder를 이용해서
    userDetailsService와 passwordEncode를 직접 설정해주어야 했지만,
    현재는 AuthenticationManager 빈 생성 시 자동으로 UserSecurityService와 PasswordEncoder가 설정된다.
    */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
