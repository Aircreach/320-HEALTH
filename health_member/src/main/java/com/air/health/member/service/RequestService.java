package com.air.health.member.service;

import com.air.health.member.entity.RequestEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.air.health.common.model.PageModel;


import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-23 18:12:06
 */
public interface RequestService extends IService<RequestEntity> {

    PageModel queryPage(Map<String, Object> params);
}

