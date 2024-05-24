package com.air.health.admin.service.impl;

import com.air.health.admin.dao.AdvisoryDao;
import com.air.health.admin.entity.AdminEntity;
import com.air.health.admin.entity.AdvisoryEntity;
import com.air.health.admin.model.AdvisoryStatus;
import com.air.health.admin.service.AdvisoryService;
import com.air.health.common.model.PageModel;
import com.air.health.common.util.Constants;
import com.air.health.common.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Service("advisoryService")
public class AdvisoryServiceImpl extends ServiceImpl<AdvisoryDao, AdvisoryEntity> implements AdvisoryService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<AdvisoryEntity> queryWrapper = new QueryWrapper<AdvisoryEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<AdvisoryEntity> entityClass = AdvisoryEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<AdvisoryEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }

    @Override
    public boolean save(AdvisoryEntity entity) {
        entity.setStatus(AdvisoryStatus.UNFINISH);
        boolean flag = super.save(entity);
        if (flag) {
            Instant instant = entity.getPublishDate().atZone(ZoneOffset.UTC).toInstant();
            redisTemplate.opsForZSet().add(Constants.REDIS_KEY_PREFIX_SCHEDULE_ADVISORY, entity.getAdvisoryId(), instant.getEpochSecond());
        }
        return flag;
    }
}