package com.air.health.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.air.health.member.feign")
@MapperScan("com.air.health.member.dao")
public class HealthMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthMemberApplication.class, args);
    }

}



