package com.air.health.member.controller;

import com.air.health.common.util.PageUtils;
import com.air.health.common.util.Result;
import com.air.health.member.entity.UserEntity;
import com.air.health.member.servcie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @Title: UserController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.controller
 * @Date 2024/4/4 21:09
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:user:list")
    public Result  list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
//    @RequiresPermissions("generator:user:info")
    public Result  info(@PathVariable("userId") Long userId){
        UserEntity user = userService.getById(userId);

        return Result.success().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:user:save")
    public Result  save(@RequestBody UserEntity user){
        userService.save(user);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:user:update")
    public Result  update(@RequestBody UserEntity user){
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

