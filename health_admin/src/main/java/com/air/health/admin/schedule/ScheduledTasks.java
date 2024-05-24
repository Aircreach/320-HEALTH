package com.air.health.admin.schedule;

import com.air.health.admin.entity.AdvisoryEntity;
import com.air.health.admin.feign.UserFeign;
import com.air.health.admin.model.AdvisoryStatus;
import com.air.health.admin.service.AdvisoryService;
import com.air.health.common.model.AirException;
import com.air.health.common.model.WSMsgModel;
import com.air.health.common.model.WSType;
import com.air.health.common.util.Constants;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

@Slf4j
@Component
public class ScheduledTasks {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    AdvisoryService advisoryService;

    @Autowired
    UserFeign userFeign;

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() throws IOException {

        long now = Instant.now().getEpochSecond();
        Set<Long> ids = redisTemplate.opsForZSet().rangeByScore(Constants.REDIS_KEY_PREFIX_SCHEDULE_ADVISORY, 0, now);
        log.info("==========检查定时任务中" + now);
        if (ids != null && !ids.isEmpty()) {
            for (Long id : ids) {
                AdvisoryEntity advisory = advisoryService.getById(id);
                if (advisory == null) {
                    redisTemplate.opsForZSet().remove(Constants.REDIS_KEY_PREFIX_SCHEDULE_ADVISORY, id);
                    throw new AirException("公告推送失败");
                }
                log.info("==========触发定时任务中" + JSON.toJSONString(advisory));
                userFeign.notify(new WSMsgModel(WSType.ADVISORY, advisory));
                advisory.setPublishDate(LocalDateTime.now(ZoneOffset.UTC));
                advisory.setStatus(AdvisoryStatus.FINISHED);
                advisoryService.updateById(advisory);
                System.out.println("Executing advisory: " + advisory.getTitle());
                // 移除已执行的任务
                redisTemplate.opsForZSet().remove(Constants.REDIS_KEY_PREFIX_SCHEDULE_ADVISORY, id);
            }
        }
    }

    // 每天上午10点执行
    @Scheduled(cron = "0 0 0 * * ?")
    public void performTask() {
        System.out.println("每天上午10点执行一次");
    }
}
