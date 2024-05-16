package com.air.health.user.servcie.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.user.dao.OrderDao;
import com.air.health.user.entity.OrderEntity;
import com.air.health.user.entity.UserEntity;
import com.air.health.user.servcie.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<OrderEntity>();
        if (params.get("extra") != null) {
            ArrayList<Map> temp = (ArrayList<Map>) params.get("extra");
            Class<OrderEntity> entityClass = OrderEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<OrderEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );

        return new PageModel(page);
    }

}