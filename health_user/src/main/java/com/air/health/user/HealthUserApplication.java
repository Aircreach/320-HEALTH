package com.air.health.user;

import com.air.health.common.config.MybatisPlusConfig;
import com.air.health.common.config.RedisConfig;
import com.air.health.common.handler.AirExceptionHandler;
import com.air.health.common.util.TokenProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSocket
@EnableFeignClients(basePackages = "com.air.health.user.feign")
@MapperScan("com.air.health.user.dao")
@Import({AirExceptionHandler.class, RedisConfig.class, TokenProvider.class, MybatisPlusConfig.class})
public class HealthUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthUserApplication.class, args);
    }

}



