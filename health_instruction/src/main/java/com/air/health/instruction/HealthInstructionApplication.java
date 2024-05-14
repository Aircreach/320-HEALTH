package com.air.health.instruction;

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

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.air.health.instruction.feign")
@MapperScan("com.air.health.instruction.dao")
@Import({AirExceptionHandler.class, RedisConfig.class, TokenProvider.class, MybatisPlusConfig.class})
public class HealthInstructionApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthInstructionApplication.class, args);
    }

}
