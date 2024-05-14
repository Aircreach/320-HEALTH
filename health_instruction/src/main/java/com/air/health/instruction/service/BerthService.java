package com.air.health.instruction.service;

import com.air.health.common.model.PageModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.air.health.instruction.entity.BerthEntity;

import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-29 20:13:31
 */
public interface BerthService extends IService<BerthEntity> {

    PageModel queryPage(Map<String, Object> params);
}

