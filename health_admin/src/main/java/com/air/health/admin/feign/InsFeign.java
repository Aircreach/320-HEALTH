package com.air.health.admin.feign;

import com.air.health.admin.interceptor.FeignClientInterceptor;
import com.air.health.common.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
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
    Result listIns(@RequestBody Map<String, Object> params);

    @PostMapping("/instruction/provinceCount")
    Result provinceCount();

    @PostMapping("/dateCount")
    Result dateCount(@RequestBody Map<String, LocalDateTime> params);

    @GetMapping("/instruction/info/{instructionId}")
//    @RequiresPermissions("generator:instruction:info")
    Result infoIns(@PathVariable("instructionId") String insId);

    @PostMapping("/instruction/update")
//    @RequiresPermissions("generator:instruction:info")
    Result updateIns(@RequestBody Object ins);

    @PostMapping("/service/list")
    Result listService(@RequestBody Map<String, Object> params);

    @GetMapping("/service/info/{serviceId}")
//    @RequiresPermissions("generator:instruction:info")
    Result infoService(@PathVariable("serviceId") String insId);
}
