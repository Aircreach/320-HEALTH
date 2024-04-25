package com.air.health.instruction.service;

import com.air.health.common.model.PageModel;
import com.air.health.instruction.entity.InstructionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Title: InstructionService
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service
 * @Date 2024/4/4 16:40
 * @description:
 */
public interface InstructionService extends IService<InstructionEntity> {

    PageModel queryPage(Map<String, Object> params);
}
