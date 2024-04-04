package com.air.health.instruction.service.impl;

import com.air.health.common.util.PageUtils;
import com.air.health.common.util.Query;
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
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InstructionEntity> page = this.page(
                new Query<InstructionEntity>().getPage(params),
                new QueryWrapper<InstructionEntity>()
        );
        return new PageUtils(page);
    }
}
