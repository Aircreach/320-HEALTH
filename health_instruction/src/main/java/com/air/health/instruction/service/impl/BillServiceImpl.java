package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.instruction.dao.BillDao;
import com.air.health.instruction.entity.BillEntity;
import com.air.health.instruction.service.BillService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title: BillServiceImpl
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service.impl
 * @Date 2024/4/4 22:03
 * @description:
 */
@Service("billService")
public class BillServiceImpl extends ServiceImpl<BillDao, BillEntity> implements BillService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        IPage<BillEntity> page = this.page(
                PageUtil.getPage(params),
                new QueryWrapper<BillEntity>()
        );
        return new PageModel(page);
    }
}
