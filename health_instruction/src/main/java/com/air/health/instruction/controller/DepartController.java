package com.air.health.instruction.controller;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.QueryModel;
import com.air.health.common.model.Result;
import com.air.health.common.util.Constants;
import com.air.health.instruction.entity.DepartEntity;
import com.air.health.instruction.entity.dto.DepartDto;
import com.air.health.instruction.feign.MemberFeign;
import com.air.health.instruction.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @Title: DepartController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.controller
 * @Date 2024/4/5 0:50
 * @description:
 */

@RestController
@RequestMapping("/depart")
public class DepartController {

    @Autowired
    private DepartService departService;

    @Autowired
    private MemberFeign memberFeign;

    /**
     * 树状查询
     * @param
     * @return
     */
    @GetMapping("/tree/{insId}")
//    @RequiresPermissions("generator:depart:list")
    public Result tree(@PathVariable("insId") Long insId){
        ArrayList<DepartDto> tree = departService.queryTree(insId);

        return Result.success().put("tree", tree);
    }


    /**
     * 列表
     */
    @PostMapping("/list")
//    @RequiresPermissions("generator:depart:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = departService.queryPage(params);

        return Result.success().put("page", page);
    }


    @PostMapping("/listMember")
    public Result listMember(@RequestParam("insId") Long insId, @RequestParam("departId") Long departId, @RequestBody Map<String, Object> params) {
        ArrayList temp = new ArrayList();
        if (params.get("extra") != null) {
            temp = (ArrayList) params.get("extra");
        }
        QueryModel query_ins = new QueryModel(Constants.EQUAL, "insId", insId);
        QueryModel query_depart = new QueryModel(Constants.EQUAL, "departId", departId);
        temp.add(query_ins);
        temp.add(query_depart);
        params.put("extra", temp);
        return memberFeign.listMember(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{departId}")
//    @RequiresPermissions("generator:depart:info")
    public Result info(@PathVariable("departId") Long departId){
        DepartEntity depart = departService.getById(departId);

        return Result.success().put("depart", depart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:depart:save")
    public Result save(@RequestBody DepartEntity depart){
        departService.save(depart);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:depart:update")
    public Result update(@RequestBody DepartEntity depart){
        departService.updateById(depart);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:depart:delete")
    public Result delete(@RequestBody Long[] departIds){
        departService.removeByIds(Arrays.asList(departIds));

        return Result.success();
    }

}