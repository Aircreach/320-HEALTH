package com.air.health.member.feign;

import com.air.health.common.model.Result;
import com.air.health.member.interceptor.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    Result listUser(@RequestBody Map<String, Object> params);

    @GetMapping("/user/info/{userId}")
    Result infoUser(@PathVariable("userId") String userId);

    @PostMapping("/order/list")
    Result listOrder(@RequestBody Map<String, Object> params);
}
