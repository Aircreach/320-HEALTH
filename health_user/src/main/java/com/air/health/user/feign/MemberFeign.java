package com.air.health.user.feign;

import com.air.health.common.model.Result;
import com.air.health.user.interceptor.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Title: MemberFeign
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.user.feign
 * @Date 2024/4/4 16:26
 * @description:
 */
@FeignClient(value = "air-health-member", configuration = FeignClientInterceptor.class)
public interface MemberFeign {
    @PostMapping("/member/list")
    Result list(@RequestBody Map<String, Object> params);

    @PostMapping("/member/count")
//    @RequiresPermissions("member:member:list")
    Result count(@RequestBody Map<String, Object> params);

    @GetMapping("/member/info/{memberId}")
    Result info(@PathVariable("memberId") Long memberId);
}
