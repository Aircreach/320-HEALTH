package com.air.health.member.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.air.health.common.model.Result;
import com.air.health.common.model.PageModel;
import com.air.health.common.model.TokenModel;
import com.air.health.common.util.Constants;
import com.air.health.common.util.TokenProvider;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.air.health.member.entity.MemberEntity;
import com.air.health.member.service.MemberService;



/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-23 11:01:02
 */
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject param) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.get("username"),
                        param.get("password")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MemberEntity user = (MemberEntity) authentication.getPrincipal();
        // 生成JWT令牌
        String token = tokenProvider.generate(Constants.TOKEN_MEMBER, user.getUsername());
        redisTemplate.opsForValue().set(
                String.format(Constants.REDIS_KEY_PREFIX_TOKEN_MEMBER, user.getUsername()),
                JSON.toJSONString(user),
                tokenProvider.getDefaultExpirationInMs(),
                TimeUnit.MILLISECONDS
        );
        return Result.success().put("token", new TokenModel(token)).put("member", user);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
//    @RequiresPermissions("member:member:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = memberService.queryPage(params);
        return Result.success().put("page", page);
    }


    /**
     * 计数
     */
    @PostMapping("/count")
//    @RequiresPermissions("member:member:list")
    public Result count(@RequestBody Map<String, Object> params){
        Long count = memberService.countData(params);
        return Result.success().put("count", count);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}")
//    @RequiresPermissions("member:member:info")
    public Result info(@PathVariable("memberId") Long memberId){
		MemberEntity member = memberService.getById(memberId);
        return Result.success().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("member:member:save")
    public Result save(@RequestBody MemberEntity member){
		memberService.save(member);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("member:member:update")
    public Result update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("member:member:delete")
    public Result delete(@RequestBody Long[] memberIds){
		memberService.removeByIds(Arrays.asList(memberIds));

        return Result.success();
    }

}
