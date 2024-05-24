package com.air.health.instruction.service;

import com.air.health.common.model.PageModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.air.health.instruction.entity.ServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:38:13
 */
public interface ServiceService extends IService<ServiceEntity> {

    PageModel queryPage(Map<String, Object> params);

    List<Long> queryAllId(Long insId);
}

