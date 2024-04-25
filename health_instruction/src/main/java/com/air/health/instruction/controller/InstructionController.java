package com.air.health.instruction.controller;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import com.air.health.instruction.entity.InstructionEntity;
import com.air.health.instruction.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @Title: InstructionControlller
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.controller
 * @Date 2024/4/4 16:39
 * @description:
 */
@RestController
@RequestMapping("/instruction")
public class InstructionController {
    @Autowired
    private InstructionService instructionService;


    /**
     *
     */

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:instruction:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageModel page = instructionService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{instructionId}")
//    @RequiresPermissions("generator:instruction:info")
    public Result info(@PathVariable("instructionId") Integer instructionId){
        InstructionEntity instruction = instructionService.getById(instructionId);

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

