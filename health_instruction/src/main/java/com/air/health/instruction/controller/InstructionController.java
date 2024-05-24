package com.air.health.instruction.controller;

import com.air.health.common.encoder.AirPasswordEncoder;
import com.air.health.common.model.*;
import com.air.health.common.util.Constants;
import com.air.health.common.util.TokenProvider;
import com.air.health.instruction.entity.InstructionEntity;
import com.air.health.instruction.feign.MemberFeign;
import com.air.health.instruction.feign.UserFeign;
import com.air.health.instruction.service.InstructionService;
import com.air.health.instruction.service.ServiceService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    private ServiceService serviceService;

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
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = instructionService.queryPage(params);

        return Result.success().put("page", page);
    }

    @PostMapping("/provinceCount")
    public Result provinceCount(){
        List<ProvinceCountModel> data = instructionService.provinceCount();
        return Result.success().put("data", data);
    }

    @PostMapping("/dateCount")
    public Result dateCount(@RequestBody Map<String, LocalDateTime> params) {
        Integer data = instructionService.dateCount(params.get("startDate"), params.get("endDate"));
        return Result.success().put("data", data);
    }


    @RequestMapping("/listUser/{instructionId}")
    public Result listUser(@PathVariable("instructionId") Long insId, @RequestBody Map<String, Object> params) {
        ArrayList extra = new ArrayList();
        if (params.get("extra") != null) {
            extra = (ArrayList) params.get("extra");
        }
        QueryModel query = new QueryModel(Constants.EQUAL, "insId", insId);
        extra.add(query);
        params.put("extra", extra);
        return userFeign.listUser(params);
    }

    @RequestMapping("/infoUser/{userId}")
    public Result infoUser(@PathVariable("userId") String userId) {
        return userFeign.infoUser(userId);
    }

    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody Object user) {
        return userFeign.updateUser(user);
    }

    @RequestMapping("/listCommonOrder/{instructionId}")
    public Result listCommonOrder(@PathVariable("instructionId") Long insId, @RequestBody Map<String, Object> params) {
        ArrayList extra = new ArrayList();
        if (params.get("extra") != null) {
            extra = (ArrayList) params.get("extra");
        }
        List<Long> ids = serviceService.queryAllId(insId);
        QueryModel query_id = new QueryModel(Constants.IN, "serviceId", ids);
        QueryModel query_type = new QueryModel(Constants.IN, "type", new int[]{0, 1, 2, 3});
        extra.add(query_id);
        extra.add(query_type);
        params.put("extra", extra);
        return userFeign.listOrder(params);
    }

    @RequestMapping("/listOccOrder/{instructionId}")
    public Result listOCCOrder(@PathVariable("instructionId") String insId, @RequestBody Map<String, Object> params) {
        ArrayList extra = new ArrayList();
        if (params.get("extra") != null) {
            extra = (ArrayList) params.get("extra");
        }
        QueryModel query_id = new QueryModel(Constants.EQUAL, "serviceId", insId);
        QueryModel query_type = new QueryModel(Constants.EQUAL, "type", 5);
        extra.add(query_id);
        extra.add(query_type);
        params.put("extra", extra);
        return userFeign.listOrder(params);
    }

    @RequestMapping("/updateOrder")
    public Result updateOrder(@RequestBody Object order) {
        return userFeign.updateOrder(order);
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
        return memberFeign.listMember(params);
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
    public Result infoMember(@PathVariable("memberId") String memberId) {
        return memberFeign.infoMember(memberId);
    }

    @PostMapping("/saveMember")
    public Result saveMember(@RequestBody Object member) {
        return memberFeign.save(member);
    }

    @PostMapping("/updateMember")
    public Result updateMember(@RequestBody Object member) {
        return memberFeign.updateMember(member);
    }

    @PostMapping("/deleteMember")
    public Result deleteMember(@RequestBody Long[] memberIds) {
        return memberFeign.delete(memberIds);
    }

    @PostMapping("/listRequest/{instructionId}")
    public Result listRequest(@PathVariable("instructionId") Long insId, @RequestBody Map<String, Object> params) {
        ArrayList temp = new ArrayList();
        if (params.get("extra") != null) {
            temp = (ArrayList) params.get("extra");
        }
        QueryModel query = new QueryModel(Constants.EQUAL, "insId", insId);
        temp.add(query);
        params.put("extra", temp);
        Map page = (Map) memberFeign.listRequest(params).get("page");
        List<Map> list = (List<Map>) page.get("list");
        list.stream().map(item -> {
            Object member = memberFeign.infoMember((String) item.get("memberId")).get("member");
            item.put("member", member);
            return item;
        }).collect(Collectors.toList());;
        page.put("list", list);
        return Result.success().put("page", page);
    }

    @PostMapping("/updateRequest")
    public Result updateRequest(@RequestBody Object request) {
        return memberFeign.updateRequest(request);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{instructionId}")
//    @RequiresPermissions("generator:instruction:info")
    public Result info(@PathVariable("instructionId") String insId){
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
        instruction.setPassword(null);
        instructionService.updateById(instruction);

        return Result.success();
    }

    /**
     * 修改密码
     */
    @RequestMapping("/changePwd")
//    @RequiresPermissions("generator:user:update")
    public Result changePwd(@RequestBody Map<String, Object> params){
        String currentPassword = (String) params.get("currentPassword");
        InstructionEntity instruction = instructionService.getById((Serializable) params.get("id"));
        AirPasswordEncoder encoder = new AirPasswordEncoder();
        if (encoder.matches(currentPassword, instruction.getPassword())) {
            LambdaUpdateWrapper<InstructionEntity> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(InstructionEntity::getId, instruction.getId())
                    .set(InstructionEntity::getPassword, encoder.encode((CharSequence) params.get("newPassword")));
            instructionService.update(null, updateWrapper);
            logout(String.valueOf(instruction.getId()));
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
//    @RequiresPermissions("generator:instruction:delete")
    public Result delete(@RequestBody Integer[] instructionIds){
        instructionService.removeByIds(Arrays.asList(instructionIds));

        return Result.success();
    }

}

