package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.instruction.dao.DepartDao;
import com.air.health.instruction.entity.DepartEntity;
import com.air.health.instruction.service.DepartService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Title: DepartServiceImpl
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service.impl
 * @Date 2024/4/5 0:46
 * @description:
 */
@Service("departService")
public class DepartServiceImpl extends ServiceImpl<DepartDao, DepartEntity> implements DepartService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<DepartEntity> queryWrapper = new QueryWrapper<DepartEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<DepartEntity> entityClass = DepartEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<DepartEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }
}
