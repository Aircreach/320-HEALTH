package com.air.health.admin.feign;

import com.air.health.admin.interceptor.FeignClientInterceptor;
import com.air.health.common.model.Result;
import com.air.health.common.model.WSMsgModel;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.time.LocalDateTime;
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

    @PostMapping("/user/provinceCount")
    Result provinceCount();

    @PostMapping("/dateCount")
    Result dateCount(@RequestBody Map<String, LocalDateTime> params);

    @PostMapping("/order/list")
    Result listOrder(@RequestBody Map<String, Object> params);

    @PostMapping("/soc/notify")
    Long notify(@RequestBody WSMsgModel msg) throws IOException;
}
