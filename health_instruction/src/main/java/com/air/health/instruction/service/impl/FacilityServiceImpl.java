package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.air.health.instruction.dao.FacilityDao;
import com.air.health.instruction.entity.FacilityEntity;
import com.air.health.instruction.service.FacilityService;


@Service("facilityService")
public class FacilityServiceImpl extends ServiceImpl<FacilityDao, FacilityEntity> implements FacilityService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        IPage<FacilityEntity> page = this.page(
                new PageUtil<FacilityEntity>().getPage(params),
                new QueryWrapper<FacilityEntity>()
        );

        return new PageModel(page);
    }

}