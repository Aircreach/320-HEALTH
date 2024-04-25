package com.air.health.instruction.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Title: InstructionFeign
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.feign
 * @Date 2024/4/4 22:21
 * @description:
 */
@FeignClient("health-instruction")
public interface InstructionFeign {
}
