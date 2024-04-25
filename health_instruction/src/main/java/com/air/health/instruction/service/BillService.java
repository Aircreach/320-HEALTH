package com.air.health.instruction.service;

import com.air.health.common.model.PageModel;
import com.air.health.instruction.entity.BillEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Title: BillService
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service
 * @Date 2024/4/4 22:01
 * @description:
 */
public interface BillService extends IService<BillEntity> {

    PageModel queryPage(Map<String, Object> params);
}
