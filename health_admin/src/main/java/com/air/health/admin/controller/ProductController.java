package com.air.health.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.air.health.admin.entity.ProductEntity;
import com.air.health.admin.service.ProductService;
import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
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
 * @date 2024-05-24 18:28:36
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:product:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageModel page = productService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{productId}")
//    @RequiresPermissions("instruction:product:info")
    public Result info(@PathVariable("productId") Long productId){
		ProductEntity product = productService.getById(productId);

        return Result.success().put("product", product);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:product:save")
    public Result save(@RequestBody ProductEntity product){
		productService.save(product);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:product:update")
    public Result update(@RequestBody ProductEntity product){
		productService.updateById(product);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:product:delete")
    public Result delete(@RequestBody Long[] productIds){
		productService.removeByIds(Arrays.asList(productIds));

        return Result.success();
    }

}
