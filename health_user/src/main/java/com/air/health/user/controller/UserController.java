package com.air.health.user.controller;

import com.air.health.common.exception.AirException;
import com.air.health.common.model.TokenModel;
import com.air.health.common.util.Constants;
import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import com.air.health.common.util.TokenProvider;
import com.air.health.user.entity.UserEntity;
import com.air.health.user.servcie.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
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

    /**
     * session-key-验证码存储
     */
    @Value("${app.common.validate}")
    private String VALIDATE = "validate";

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject param) {
        log.info("触发登录=========={}, {}", param.toString());
        String validate = (String) redisTemplate.opsForValue().get(String.format(VALIDATE + "%s", param.get("uuid")));
        if (validate == null || !validate.equals(param.get("kaptcha").toString())) {
            throw new AirException("ERROR => 验证码错误");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.get("username"),
                        param.get("password")
                )
        );
        UserEntity user = (UserEntity)authentication.getPrincipal();
        // 设置安全上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成JWT令牌
        String token = tokenProvider.generate(user.getUsername());
        redisTemplate.opsForValue().set(
                String.format(Constants.REDIS_KEY_PREFIX_TOKEN, user.getUserId()),
                JSON.toJSONString(user),
                tokenProvider.getExpirationInMs(),
                TimeUnit.MILLISECONDS
        );
        return Result.success().put("token", new TokenModel(token));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:user:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageModel page = userService.queryPage(params);

        return Result.success().put("page", page);
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

