package com.air.health.instruction.feign;

import com.air.health.common.model.Result;
import com.air.health.instruction.interceptor.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Title: InstructionFeign
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.feign
 * @Date 2024/4/4 22:21
 * @description:
 */
@FeignClient(value = "air-health-user", configuration = FeignClientInterceptor.class)
public interface UserFeign {
    @PostMapping("/user/list")
    Result list(@RequestBody Map<String, Object> params);
}
