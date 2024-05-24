package com.air.health.user.controller;

import java.util.Arrays;
import java.util.Map;


import com.air.health.common.model.PageModel;
import com.air.health.common.model.Result;
import com.air.health.user.entity.EvaluationEntity;
import com.air.health.user.servcie.EvaluationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
 * @date 2024-05-19 09:18:44
 */
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:evaluation:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = evaluationService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{evalId}")
//    @RequiresPermissions("instruction:evaluation:info")
    public Result info(@PathVariable("evalId") Long evalId){
		EvaluationEntity evaluation = evaluationService.getById(evalId);

        return Result.success().put("evaluation", evaluation);
    }

    @RequestMapping("/count/{insId}")
    public Result count(@PathVariable("insId") String insId){
        LambdaQueryWrapper<EvaluationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EvaluationEntity::getInsId, insId);
        long num = evaluationService.count(queryWrapper);

        return Result.success().put("count", num);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:evaluation:save")
    public Result save(@RequestBody EvaluationEntity evaluation){
		evaluationService.save(evaluation);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:evaluation:update")
    public Result update(@RequestBody EvaluationEntity evaluation){
		evaluationService.updateById(evaluation);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:evaluation:delete")
    public Result delete(@RequestBody Long[] evalIds){
		evaluationService.removeByIds(Arrays.asList(evalIds));

        return Result.success();
    }

}
