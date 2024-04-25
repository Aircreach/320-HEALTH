package com.air.health.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.air.health.common.model.Result;
import com.air.health.common.model.PageModel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
