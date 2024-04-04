package com.air.health.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HealthMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthMemberApplication.class, args);
    }

}


