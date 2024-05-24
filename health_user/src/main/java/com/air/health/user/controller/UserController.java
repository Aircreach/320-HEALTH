package com.air.health.user.controller;

import com.air.health.common.encoder.AirPasswordEncoder;
import com.air.health.common.model.*;
import com.air.health.common.util.Constants;
import com.air.health.common.util.IPUtil;
import com.air.health.common.util.TokenProvider;
import com.air.health.user.entity.OrderEntity;
import com.air.health.user.entity.UserEntity;
import com.air.health.user.feign.InsFeign;
import com.air.health.user.servcie.UserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
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
     * 支付
     */
    @PostMapping("/purchase")
    public Result purchase(@RequestBody OrderEntity order){
        Long balance = userService.purchase(order);
        return Result.success().put("balance", balance);
    }

    @PostMapping("/provinceCount")
    public Result provinceCount(){
        List<ProvinceCountModel> data = userService.provinceCount();
        return Result.success().put("data", data);
    }

    @PostMapping("/dateCount")
    public Result dateCount(@RequestBody Map<String, LocalDateTime> params){
        Integer data = userService.dateCount(params.get("startDate"), params.get("endDate"));
        return Result.success().put("data", data);
    }

    /**
     * 查询机构
     */
    @PostMapping("/listIns")
    public Result listIns(@RequestBody Map<String, Object> params){
        return insFeign.listIns(params);
    }

    @GetMapping("/infoIns/{instructionId}")
    public Result infoIns(@PathVariable("instructionId") String insId){
        return insFeign.infoIns(insId);
    }

    @PostMapping("/listService/{instructionId}")
    public Result listService(@PathVariable("instructionId") String insId, @RequestBody Map<String, Object> params){
        ArrayList<Object> extra = new ArrayList();
        QueryModel query = new QueryModel(Constants.EQUAL, "insId", insId);
        extra.add(query);
        params.put("extra", extra);
        return insFeign.listService(params);
    }

    @GetMapping("/infoService/{serviceId}")
    public Result infoService(@PathVariable("serviceId") String insId){
        return insFeign.infoService(insId);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
//    @RequiresPermissions("generator:user:info")
    public Result info(@PathVariable("userId") String userId){
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
        user.setPassword(null);
        userService.updateById(user);

        return Result.success();
    }

    /**
     * 修改密码
     */
    @RequestMapping("/changePwd")
//    @RequiresPermissions("generator:user:update")
    public Result changePwd(@RequestBody Map<String, Object> params){
        String currentPassword = (String) params.get("currentPassword");
        UserEntity user = userService.getById((Serializable) params.get("id"));
        AirPasswordEncoder encoder = new AirPasswordEncoder();
        if (encoder.matches(currentPassword, user.getPassword())) {
            LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(UserEntity::getUserId, user.getUserId())
                    .set(UserEntity::getPassword, params.get("newPassword"));
            userService.update(null, updateWrapper);
            logout(String.valueOf(user.getUserId()));
            return Result.success();
        } else {
            throw new AirException("密码错误");
        }
    }

    /**
     * 登出
     */
    @GetMapping("/logout/{id}")
    public Result logout(@PathVariable("id") String id){
        redisTemplate.delete("id");
        SecurityContextHolder.clearContext();
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

