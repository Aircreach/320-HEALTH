package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.instruction.dao.InstructionDao;
import com.air.health.instruction.entity.InstructionEntity;
import com.air.health.instruction.service.InstructionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title: InstructionServiceImpl
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service.impl
 * @Date 2024/4/4 16:53
 * @description:
 */
@Service("instructionService")
public class InstructionServiceImpl extends ServiceImpl<InstructionDao, InstructionEntity> implements InstructionService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        IPage<InstructionEntity> page = this.page(
                PageUtil.getPage(params),
                new QueryWrapper<InstructionEntity>()
        );
        return new PageModel(page);
    }
}
