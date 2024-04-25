package com.air.health.instruction.controller;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import com.air.health.instruction.entity.BillEntity;
import com.air.health.instruction.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @Title: BillController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.controller
 * @Date 2024/4/4 22:07
 * @description:
 */
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:bill:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageModel page = billService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{billId}")
//    @RequiresPermissions("generator:bill:info")
    public Result info(@PathVariable("billId") Long billId){
        BillEntity bill = billService.getById(billId);

        return Result.success().put("bill", bill);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:bill:save")
    public Result save(@RequestBody BillEntity bill){
        billService.save(bill);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:bill:update")
    public Result update(@RequestBody BillEntity bill){
        billService.updateById(bill);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:bill:delete")
    public Result delete(@RequestBody Long[] billIds){
        billService.removeByIds(Arrays.asList(billIds));

        return Result.success();
    }

}


