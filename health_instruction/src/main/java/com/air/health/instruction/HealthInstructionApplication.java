package com.air.health.instruction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HealthInstructionApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthInstructionApplication.class, args);
    }

}
