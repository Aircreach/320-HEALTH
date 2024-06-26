package com.air.health.user.controller;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import com.air.health.user.entity.MedicalRecordEntity;
import com.air.health.user.servcie.MedialRecordService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @Title: medicalRecordController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.controller
 * @Date 2024/4/4 21:37
 * @description:
 */
@RefreshScope
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    @Autowired
    private MedialRecordService medicalRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:medicalRecord:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageModel page = medicalRecordService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{medicalRecordId}")
//    @RequiresPermissions("generator:medicalRecord:info")
    public Result info(@PathVariable("medicalRecordId") Long medicalRecordId){
        MedicalRecordEntity medicalRecord = medicalRecordService.getById(medicalRecordId);

        return Result.success().put("medicalRecord", medicalRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:medicalRecord:save")
    public Result save(@RequestBody MedicalRecordEntity medicalRecord){
        medicalRecordService.save(medicalRecord);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:medicalRecord:update")
    public Result update(@RequestBody MedicalRecordEntity medicalRecord){
        medicalRecordService.updateById(medicalRecord);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:medicalRecord:delete")
    public Result delete(@RequestBody Long[] medicalRecordIds){
        medicalRecordService.removeByIds(Arrays.asList(medicalRecordIds));

        return Result.success();
    }

}

