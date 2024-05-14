package com.air.health.user.feign;

import com.air.health.common.model.Result;
import com.air.health.user.interceptor.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Title: MemberFeign
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.feign
 * @Date 2024/4/4 16:26
 * @description:
 */
@FeignClient(value = "air-health-instruction", configuration = FeignClientInterceptor.class)
public interface InsFeign {
    @PostMapping("/instruction/list")
    Result list(@RequestBody Map<String, Object> params);
}
