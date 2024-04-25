package com.air.health.member.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Title: MemberFeign
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.feign
 * @Date 2024/4/4 16:26
 * @description:
 */
@FeignClient("health-member")
public interface MemberFeign {
//    @Autowired
//    private RoleService remoteService;
//
//
//    @PostMapping("/")
}
