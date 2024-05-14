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

import com.air.health.instruction.entity.FacilityEntity;
import com.air.health.instruction.service.FacilityService;



/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-29 20:13:31
 */
@RestController
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:facility:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageModel page = facilityService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{facilityId}")
//    @RequiresPermissions("instruction:facility:info")
    public Result info(@PathVariable("facilityId") Long facilityId){
		FacilityEntity facility = facilityService.getById(facilityId);

        return Result.success().put("facility", facility);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:facility:save")
    public Result save(@RequestBody FacilityEntity facility){
		facilityService.save(facility);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:facility:update")
    public Result update(@RequestBody FacilityEntity facility){
		facilityService.updateById(facility);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:facility:delete")
    public Result delete(@RequestBody Long[] facilityIds){
		facilityService.removeByIds(Arrays.asList(facilityIds));

        return Result.success();
    }

}
