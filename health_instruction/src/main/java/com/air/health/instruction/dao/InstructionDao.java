package com.air.health.instruction.dao;

import com.air.health.instruction.entity.InstructionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: InstructionDao
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.Dao
 * @Date 2024/4/4 13:39
 * @description:
 */
@Mapper
public interface InstructionDao extends BaseMapper<InstructionEntity> {
}
