package com.air.health.admin.service;

import com.air.health.admin.entity.ProductEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.air.health.common.model.PageModel;

import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-24 18:28:36
 */
public interface ProductService extends IService<ProductEntity> {

    PageModel queryPage(Map<String, Object> params);
}

