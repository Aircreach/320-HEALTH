package com.air.health.instruction.feign;

import com.air.health.common.model.Result;
import com.air.health.instruction.interceptor.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: MemberFeign
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.feign
 * @Date 2024/4/4 16:26
 * @description:
 */
@FeignClient(value = "air-health-member", configuration = FeignClientInterceptor.class)
public interface MemberFeign {
    @PostMapping("/member/list")
    Result listMember(@RequestBody Map<String, Object> params);

    @PostMapping("/member/count")
//    @RequiresPermissions("member:member:list")
    Result count(@RequestBody Map<String, Object> params);

    @GetMapping("/member/info/{memberId}")
    Result infoMember(@PathVariable("memberId") String memberId);

    @PostMapping("/member/save")
//    @RequiresPermissions("member:member:save")
    Result save(@RequestBody Object member);

    @PostMapping("/member/update")
//    @RequiresPermissions("member:member:save")
    Result updateMember(@RequestBody Object member);
    @PostMapping("/member/delete")
    Result delete(@RequestBody Long[] memberIds);

    @PostMapping("/request/list")
//    @RequiresPermissions("instruction:request:list")
    Result listRequest(@RequestBody Map<String, Object> params);

    @PostMapping("/request/update")
    Result updateRequest(@RequestBody Object request);
}
