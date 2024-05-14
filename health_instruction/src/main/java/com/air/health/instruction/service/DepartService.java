package com.air.health.instruction.service;

import com.air.health.common.model.PageModel;
import com.air.health.instruction.entity.DepartEntity;
import com.air.health.instruction.entity.dto.DepartDto;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.swing.tree.TreeModel;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Title: DepartService
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service
 * @Date 2024/4/5 0:46
 * @description:
 */
public interface DepartService extends IService<DepartEntity> {

    ArrayList<DepartDto> queryTree(Long insId);

    PageModel queryPage(Map<String, Object> params);
}
