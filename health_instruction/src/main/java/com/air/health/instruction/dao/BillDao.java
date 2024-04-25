package com.air.health.instruction.dao;

import com.air.health.instruction.entity.BillEntity;
import com.air.health.instruction.entity.InstructionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: BillDao
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.dao
 * @Date 2024/4/4 22:01
 * @description:
 */
@Mapper
public interface BillDao extends BaseMapper<BillEntity> {
}
