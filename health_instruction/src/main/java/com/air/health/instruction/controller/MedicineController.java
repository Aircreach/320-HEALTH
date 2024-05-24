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

import com.air.health.instruction.entity.MedicineEntity;
import com.air.health.instruction.service.MedicineService;



/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:55:15
 */
@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:medicine:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = medicineService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{medicineId}")
//    @RequiresPermissions("instruction:medicine:info")
    public Result info(@PathVariable("medicineId") Long medicineId){
		MedicineEntity medicine = medicineService.getById(medicineId);

        return Result.success().put("medicine", medicine);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:medicine:save")
    public Result save(@RequestBody MedicineEntity medicine){
		medicineService.save(medicine);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:medicine:update")
    public Result update(@RequestBody MedicineEntity medicine){
		medicineService.updateById(medicine);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:medicine:delete")
    public Result delete(@RequestBody Long[] medicineIds){
		medicineService.removeByIds(Arrays.asList(medicineIds));

        return Result.success();
    }

}
