package com.air.health.instruction.controller;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import com.air.health.instruction.entity.DepartEntity;
import com.air.health.instruction.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @Title: DepartController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.controller
 * @Date 2024/4/5 0:50
 * @description:
 */

@RestController
@RequestMapping("/depart")
public class DepartController {
    @Autowired
    private DepartService departService;

    /**
     * 树状查询
     * @param params
     * @return
     */
    @GetMapping("/tree")
//    @RequiresPermissions("generator:depart:list")
    public Result tree(@RequestBody Map<String, Object> params){
        PageModel page = departService.queryPage(params);

        return Result.success().put("tree", page);
    }


    /**
     * 列表
     */
    @PostMapping("/list")
//    @RequiresPermissions("generator:depart:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = departService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{departId}")
//    @RequiresPermissions("generator:depart:info")
    public Result info(@PathVariable("departId") Long departId){
        DepartEntity depart = departService.getById(departId);

        return Result.success().put("depart", depart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:depart:save")
    public Result save(@RequestBody DepartEntity depart){
        departService.save(depart);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:depart:update")
    public Result update(@RequestBody DepartEntity depart){
        departService.updateById(depart);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:depart:delete")
    public Result delete(@RequestBody Long[] departIds){
        departService.removeByIds(Arrays.asList(departIds));

        return Result.success();
    }

}