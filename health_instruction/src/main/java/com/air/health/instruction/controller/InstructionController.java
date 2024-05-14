package com.air.health.instruction.controller;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.QueryModel;
import com.air.health.common.model.Result;
import com.air.health.common.model.TokenModel;
import com.air.health.common.util.Constants;
import com.air.health.common.util.PageUtil;
import com.air.health.common.util.TokenProvider;
import com.air.health.instruction.entity.InstructionEntity;
import com.air.health.instruction.feign.MemberFeign;
import com.air.health.instruction.feign.UserFeign;
import com.air.health.instruction.service.InstructionService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title: InstructionControlller
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.controller
 * @Date 2024/4/4 16:39
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/instruction")
public class InstructionController {
    @Autowired
    private InstructionService instructionService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private MemberFeign memberFeign;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody JSONObject param) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.get("username"),
                        param.get("password")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        InstructionEntity instruction = (InstructionEntity) authentication.getPrincipal();
        // 生成JWT令牌
        String token = tokenProvider.generate(Constants.TOKEN_INS, instruction.getUsername());
        redisTemplate.opsForValue().set(
                String.format(Constants.REDIS_KEY_PREFIX_TOKEN_INS, instruction.getUsername()),
                JSON.toJSONString(instruction),
                tokenProvider.getDefaultExpirationInMs(),
                TimeUnit.MILLISECONDS
        );
        return Result.success().put("token", new TokenModel(token)).put("instruction", instruction);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:instruction:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageModel page = instructionService.queryPage(params);

        return Result.success().put("page", page);
    }


    @RequestMapping("/listUser/{instructionId}")
    public Result listUser(@PathVariable("instructionId") Long insId) {
        ArrayList<Object> extra = new ArrayList();
        QueryModel query = new QueryModel(Constants.EQUAL, "insId", insId);
        extra.add(query);
        HashMap<String, Object> map = new HashMap<>();
        map.put("extra", extra);
        return userFeign.list(map);
    }


    @PostMapping("/listMember/{instructionId}")
    public Result listMember(@PathVariable("instructionId") Long insId, @RequestBody Map<String, Object> params) {
        ArrayList temp = new ArrayList();
        if (params.get("extra") != null) {
           temp = (ArrayList) params.get("extra");
        }
        QueryModel query = new QueryModel(Constants.EQUAL, "insId", insId);
        temp.add(query);
        params.put("extra", temp);
        return memberFeign.list(params);
    }

    @PostMapping("/countMember/{instructionId}")
    public Result countMember(@PathVariable("instructionId") Long insId, @RequestBody Map<String, Object> params) {
        ArrayList temp = new ArrayList();
        if (params.get("extra") != null) {
            temp = (ArrayList) params.get("extra");
        }
        QueryModel query = new QueryModel(Constants.EQUAL, "insId", insId);
        temp.add(query);
        params.put("extra", temp);
        return memberFeign.count(params);
    }

    @GetMapping("/infoMember/{memberId}")
    public Result infoMember(@PathVariable("memberId") Long memberId) {
        return memberFeign.info(memberId);
    }

    @PostMapping("/saveMember")
    public Result saveMember(@RequestBody Object member) {
        return memberFeign.save(member);
    }

    @PostMapping("/updateMember")
    public Result updateMember(@RequestBody Object member) {
        return memberFeign.update(member);
    }

    @PostMapping("/deleteMember")
    public Result deleteMember(@RequestBody Long[] memberIds) {
        return memberFeign.delete(memberIds);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{instructionId}")
//    @RequiresPermissions("generator:instruction:info")
    public Result info(@PathVariable("instructionId") Integer insId){
        InstructionEntity instruction = instructionService.getById(insId);
        return Result.success().put("instruction", instruction);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:instruction:save")
    public Result save(@RequestBody InstructionEntity instruction){
        instructionService.save(instruction);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:instruction:update")
    public Result update(@RequestBody InstructionEntity instruction){
        instructionService.updateById(instruction);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:instruction:delete")
    public Result delete(@RequestBody Integer[] instructionIds){
        instructionService.removeByIds(Arrays.asList(instructionIds));

        return Result.success();
    }

}

