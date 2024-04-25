package com.air.health.user.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Title: MemberFeign
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.feign
 * @Date 2024/4/4 16:26
 * @description:
 */
@FeignClient("health-user")
public interface UserFeign {
//    @Autowired
//    private RoleService remoteService;
//
//
//    @PostMapping("/")
}
