package com.air.health.admin.config;

import com.air.health.admin.filter.TokenAuthenticationFilter;
import com.air.health.admin.security.AdminAuthenticationEntryPoint;
import com.air.health.admin.service.AdminService;
import com.air.health.common.encoder.AirPasswordEncoder;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Title: SecurityConfig
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.admin.config
 * @Date 2024/4/13 15:21
 * @description:
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    AdminService adminService;

    @Autowired
    TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    AdminAuthenticationEntryPoint adminAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager() throws RuntimeException {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(adminService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        ProviderManager manager = new ProviderManager(daoAuthenticationProvider);
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/login", "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasicConfigurer -> {
                    httpBasicConfigurer.authenticationEntryPoint(adminAuthenticationEntryPoint);
                })
                .formLogin(FormLoginConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .rememberMe(Customizer.withDefaults())
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        //配置头部
        http.headers(headers -> headers
                .contentTypeOptions(HeadersConfigurer.ContentTypeOptionsConfig::disable)
        );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return new AirPasswordEncoder();
    }
}
