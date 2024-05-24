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

import com.air.health.instruction.entity.ServiceEntity;
import com.air.health.instruction.service.ServiceService;




/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:38:13
 */
@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:service:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = serviceService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{serviceId}")
//    @RequiresPermissions("instruction:service:info")
    public Result info(@PathVariable("serviceId") String serviceId){
		ServiceEntity service = serviceService.getById(serviceId);

        return Result.success().put("service", service);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:service:save")
    public Result save(@RequestBody ServiceEntity service){
		serviceService.save(service);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:service:update")
    public Result update(@RequestBody ServiceEntity service){
		serviceService.updateById(service);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:service:delete")
    public Result delete(@RequestBody Long[] serviceIds){
		serviceService.removeByIds(Arrays.asList(serviceIds));

        return Result.success();
    }

}
