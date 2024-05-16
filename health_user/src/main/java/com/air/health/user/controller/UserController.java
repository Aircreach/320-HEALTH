package com.air.health.user.controller;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import com.air.health.common.model.TokenModel;
import com.air.health.common.util.Constants;
import com.air.health.common.util.IPUtil;
import com.air.health.common.util.TokenProvider;
import com.air.health.user.entity.UserEntity;
import com.air.health.user.feign.InsFeign;
import com.air.health.user.servcie.UserService;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title: UserController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.controller
 * @Date 2024/4/4 21:09
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private InsFeign insFeign;


    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody JSONObject param) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.get("username"),
                        param.get("password")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String region = IPUtil.getIPRegion(request);
        if (region != null) {
            user.setAddress(region);
            log.info("=============={}", region);
            userService.updateById(user);
        }
        // 生成JWT令牌
        String token = tokenProvider.generate(Constants.TOKEN_USER, user.getUsername());
        redisTemplate.opsForValue().set(
                String.format(Constants.REDIS_KEY_PREFIX_TOKEN_USER, user.getUsername()),
                JSON.toJSONString(user),
                tokenProvider.getDefaultExpirationInMs(),
                TimeUnit.MILLISECONDS
        );
        return Result.success().put("token", new TokenModel(token)).put("user", user);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
//    @RequiresPermissions("generator:user:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = userService.queryPage(params);

        return Result.success().put("page", page);
    }

    /**
     * 查询机构
     */
    @PostMapping("/listIns")
    public Result listIns(@RequestBody Map<String, Object> params){

        return insFeign.list(params);
    }

    @GetMapping("/infoIns/{instructionId}")
    public Result infoIns(@PathVariable("instructionId") String insId){
        return insFeign.info(insId);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
//    @RequiresPermissions("generator:user:info")
    public Result info(@PathVariable("userId") Long userId){
        UserEntity user = userService.getById(userId);

        return Result.success().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:user:save")
    public Result save(@RequestBody UserEntity user){
        userService.save(user);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:user:update")
    public Result update(@RequestBody UserEntity user){
        userService.updateById(user);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:user:delete")
    public Result delete(@RequestBody Long[] userIds){
        userService.removeByIds(Arrays.asList(userIds));
        return Result.success();
    }
}

