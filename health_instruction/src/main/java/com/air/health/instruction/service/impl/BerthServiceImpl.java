package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.air.health.instruction.dao.BerthDao;
import com.air.health.instruction.entity.BerthEntity;
import com.air.health.instruction.service.BerthService;

@Slf4j
@Service("berthService")
public class BerthServiceImpl extends ServiceImpl<BerthDao, BerthEntity> implements BerthService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<BerthEntity> queryWrapper;
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<BerthEntity> entityClass = BerthEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        } else {
            queryWrapper  = new QueryWrapper<BerthEntity>();
        }
        IPage<BerthEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }

}