package com.air.health.user.servcie;

import com.air.health.common.model.PageModel;
import com.air.health.user.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:38:13
 */
public interface OrderService extends IService<OrderEntity> {

    PageModel queryPage(Map<String, Object> params);
}

