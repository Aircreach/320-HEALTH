package com.air.health.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.socket.client.TomcatWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import org.springframework.web.reactive.socket.server.RequestUpgradeStrategy;
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy;

/**
 * @Title: CorsConfiguration
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.gateway.config
 * @Date 2024/4/7 11:40
 * @description:
 */
@Configuration
public class CorsConfiguration {

    @Bean
    public CorsWebFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
        // 配置跨域
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOriginPattern("*");
        // setAllowCredentials为true，则config.addAllowedOrigin("*")的参数就不能是 * -> 使用addAllowedOriginPattern
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**" , corsConfiguration);
        return new CorsWebFilter(source);
    }


    @Bean
    @Primary
    public RequestUpgradeStrategy requestUpgradeStrategy() {
        return new TomcatRequestUpgradeStrategy();
    }

    @Bean
    @Primary
    public WebSocketClient webSocketClient() {
        return new TomcatWebSocketClient();
    }
}
