package com.air.health.member.controller;

import java.util.*;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.QueryModel;
import com.air.health.common.model.Result;
import com.air.health.common.util.Constants;
import com.air.health.member.entity.RequestEntity;
import com.air.health.member.service.RequestService;
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
 * @date 2024-05-23 18:12:06
 */
@RestController
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:request:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = requestService.queryPage(params);

        return Result.success().put("page", page);
    }

    @RequestMapping("/query/{memberId}")
    public Result query(@PathVariable("memberId") String memberId){
        LambdaQueryWrapper<RequestEntity> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(RequestEntity::getMemberId, memberId);
        queryWrapper.ne(RequestEntity::getRequestType, 4);
        List<RequestEntity> list = requestService.list(queryWrapper);

        return Result.success().put("list", list);
    }

    @RequestMapping("/listVacation/{memberId}")
    public Result listVacation(@PathVariable("memberId") String memberId, @RequestBody Map<String, Object> params){
        ArrayList temp = new ArrayList();
        if (params.get("extra") != null) {
            temp = (ArrayList) params.get("extra");
        }
        Map<String, Object> queryId = new HashMap<>();
        queryId.put(Constants.METHOD, Constants.EQUAL);
        queryId.put(Constants.FIELD, "memberId");
        queryId.put(Constants.VALUE, memberId);
        Map<String, Object> queryType = new HashMap<>();
        queryType.put(Constants.METHOD, Constants.EQUAL);
        queryType.put(Constants.FIELD, "requestType");
        queryType.put(Constants.VALUE, 4);
        temp.add(queryId);
        temp.add(queryType);
        params.put("extra", temp);
        PageModel page = requestService.queryPage(params);
        return Result.success().put("page", page);
    }

    @RequestMapping("/queryVacation/{memberId}")
    public Result queryVacation(@PathVariable("memberId") String memberId){
        LambdaQueryWrapper<RequestEntity> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(RequestEntity::getMemberId, memberId);
        queryWrapper.eq(RequestEntity::getRequestType, 4);
        RequestEntity request = requestService.getOne(queryWrapper);

        return Result.success().put("vacation", request);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{requestId}")
    public Result info(@PathVariable("requestId") Long requestId){
		RequestEntity request = requestService.getById(requestId);

        return Result.success().put("request", request);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:request:save")
    public Result save(@RequestBody RequestEntity request){
		requestService.save(request);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:request:update")
    public Result update(@RequestBody RequestEntity request){
		requestService.updateById(request);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:request:delete")
    public Result delete(@RequestBody Long[] requestIds){
		requestService.removeByIds(Arrays.asList(requestIds));

        return Result.success();
    }

}
