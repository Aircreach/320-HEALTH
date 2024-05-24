package com.air.health.user.servcie;

import com.air.health.user.entity.EvaluationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.air.health.common.model.PageModel;

import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-19 09:18:44
 */
public interface EvaluationService extends IService<EvaluationEntity> {

    PageModel queryPage(Map<String, Object> params);
}

