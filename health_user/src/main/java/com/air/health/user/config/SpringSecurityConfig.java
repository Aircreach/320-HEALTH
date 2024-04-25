package com.air.health.user.config;

import com.air.health.user.servcie.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Title: SecurityConfig
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.user.config
 * @Date 2024/4/13 15:21
 * @description:
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    public AuthenticationManager authenticationManager() throws RuntimeException {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        ProviderManager manager = new ProviderManager(daoAuthenticationProvider);
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
//                        .anyRequest().authenticated()
                )
                .formLogin(FormLoginConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .rememberMe(Customizer.withDefaults());

        //配置头部
        http.headers(headers -> headers
                .contentTypeOptions(HeadersConfigurer.ContentTypeOptionsConfig::disable)
        );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 配置 HttpSessionIdResolver Bean
     * 登录之后将会在 Response Header x-auth-token 中 返回当前 sessionToken
     * 将token存储在前端 每次调用的时候 Request Header x-auth-token 带上 sessionToken
     */
//    @Bean
//    public HttpSessionIdResolver httpSessionIdResolver() {
//        return HeaderHttpSessionIdResolver.xAuthToken();
//    }
}
