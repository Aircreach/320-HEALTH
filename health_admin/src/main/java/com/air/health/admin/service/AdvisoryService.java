package com.air.health.admin.service;


import com.air.health.admin.entity.AdvisoryEntity;
import com.air.health.common.model.PageModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-18 17:37:48
 */
public interface AdvisoryService extends IService<AdvisoryEntity> {

    PageModel queryPage(Map<String, Object> params);
}

