package com.air.health.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Title: GatewayFeign
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.gateway.feign
 * @Date 2024/4/7 11:13
 * @description:
 */
@FeignClient("health-gateway")
public interface GatewayFeign {
}
