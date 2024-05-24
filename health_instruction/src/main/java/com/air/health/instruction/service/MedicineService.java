package com.air.health.instruction.service;

import com.air.health.common.model.PageModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.air.health.instruction.entity.MedicineEntity;

import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:55:15
 */
public interface MedicineService extends IService<MedicineEntity> {

    PageModel queryPage(Map<String, Object> params);
}

