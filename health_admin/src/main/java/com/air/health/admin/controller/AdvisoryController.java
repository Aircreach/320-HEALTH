package com.air.health.admin.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Map;

import com.air.health.admin.entity.AdvisoryEntity;
import com.air.health.admin.feign.UserFeign;
import com.air.health.admin.model.AdvisoryStatus;
import com.air.health.admin.service.AdvisoryService;
import com.air.health.common.model.*;
import com.air.health.common.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
 * @date 2024-05-18 17:37:48
 */
@RestController
@RequestMapping("/advisory")
public class AdvisoryController {
    @Autowired
    private AdvisoryService advisoryService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private UserFeign userFeign;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("instruction:sysadvisory:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = advisoryService.queryPage(params);

        return Result.success().put("page", page);
    }

    @RequestMapping("/publish/{advisoryId}")
    public Result publish(@PathVariable("advisoryId") String advisoryId) throws IOException {
        AdvisoryEntity advisory = advisoryService.getById(advisoryId);
        if (advisory == null) {
            throw  new AirException("未知错误");
        }
        redisTemplate.opsForZSet().remove(Constants.REDIS_KEY_PREFIX_SCHEDULE_ADVISORY, advisory.getAdvisoryId());
        Long num = userFeign.notify(new WSMsgModel(WSType.ADVISORY, advisory));
        advisory.setPublishDate(LocalDateTime.now(ZoneOffset.UTC));
        advisory.setStatus(AdvisoryStatus.FINISHED);
        advisoryService.updateById(advisory);
        return Result.success().put("num", num);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{advisoryId}")
//    @RequiresPermissions("instruction:sysadvisory:info")
    public Result info(@PathVariable("advisoryId") Long advisoryId){
		AdvisoryEntity sysAdvisory = advisoryService.getById(advisoryId);

        return Result.success().put("advisory", sysAdvisory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("instruction:sysadvisory:save")
    public Result save(@RequestBody AdvisoryEntity sysAdvisory){
		advisoryService.save(sysAdvisory);
        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("instruction:sysadvisory:update")
    public Result update(@RequestBody AdvisoryEntity sysAdvisory){
		boolean flag = advisoryService.updateById(sysAdvisory);
        if (flag) {
            Instant instant = sysAdvisory.getPublishDate().atZone(ZoneOffset.UTC).toInstant();
            redisTemplate.opsForZSet().remove(Constants.REDIS_KEY_PREFIX_SCHEDULE_ADVISORY, sysAdvisory.getAdvisoryId());
            redisTemplate.opsForZSet().add(Constants.REDIS_KEY_PREFIX_SCHEDULE_ADVISORY, sysAdvisory.getAdvisoryId(), instant.getEpochSecond());

        }
        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("instruction:sysadvisory:delete")
    public Result delete(@RequestBody Long[] advisoryIds){
		advisoryService.removeByIds(Arrays.asList(advisoryIds));
        for (Long advisoryId : advisoryIds) {
            redisTemplate.opsForZSet().remove(Constants.REDIS_KEY_PREFIX_SCHEDULE_ADVISORY, advisoryId);
        }
        return Result.success();
    }

}
