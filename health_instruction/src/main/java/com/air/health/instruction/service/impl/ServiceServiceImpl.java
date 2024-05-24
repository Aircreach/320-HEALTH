package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.air.health.instruction.dao.ServiceDao;
import com.air.health.instruction.entity.ServiceEntity;
import com.air.health.instruction.service.ServiceService;


@Service
public class ServiceServiceImpl extends ServiceImpl<ServiceDao, ServiceEntity> implements ServiceService {

    @Autowired
    ServiceDao serviceDao;

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<ServiceEntity> queryWrapper = new QueryWrapper<ServiceEntity>();
        if (params.get("extra") != null) {
            ArrayList<Map> temp = (ArrayList<Map>) params.get("extra");
            Class<ServiceEntity> entityClass = ServiceEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<ServiceEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );

        return new PageModel(page);
    }

    @Override
    public List<Long> queryAllId(Long insId) {
        List<Long> ids = serviceDao.selectAllIds(insId);
        return ids;
    }

}