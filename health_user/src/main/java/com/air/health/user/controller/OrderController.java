package com.air.health.user.controller;

import java.util.Arrays;
import java.util.Map;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import com.air.health.user.entity.OrderEntity;
import com.air.health.user.servcie.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:38:13
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:order:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageModel page = orderService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
//    @RequiresPermissions("instruction:order:info")
    public Result info(@PathVariable("orderId") Long orderId){
		OrderEntity order = orderService.getById(orderId);

        return Result.success().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:order:save")
    public Result save(@RequestBody OrderEntity order){
		orderService.save(order);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:order:update")
    public Result update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:order:delete")
    public Result delete(@RequestBody Long[] orderIds){
		orderService.removeByIds(Arrays.asList(orderIds));

        return Result.success();
    }

}
