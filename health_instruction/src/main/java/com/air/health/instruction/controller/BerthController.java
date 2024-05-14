package com.air.health.instruction.controller;

import java.util.Arrays;
import java.util.Map;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.air.health.instruction.entity.BerthEntity;
import com.air.health.instruction.service.BerthService;



/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-29 20:13:31
 */
@RestController
@RequestMapping("/berth")
public class BerthController {
    @Autowired
    private BerthService berthService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:berth:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = berthService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{berthId}")
//    @RequiresPermissions("instruction:berth:info")
    public Result info(@PathVariable("berthId") Long berthId){
		BerthEntity berth = berthService.getById(berthId);

        return Result.success().put("berth", berth);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:berth:save")
    public Result save(@RequestBody BerthEntity berth){
		berthService.save(berth);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:berth:update")
    public Result update(@RequestBody BerthEntity berth){
		berthService.updateById(berth);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:berth:delete")
    public Result delete(@RequestBody Long[] berthIds){
		berthService.removeByIds(Arrays.asList(berthIds));

        return Result.success();
    }

}
