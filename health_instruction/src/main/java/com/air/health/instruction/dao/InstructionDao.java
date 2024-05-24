package com.air.health.instruction.dao;

import com.air.health.common.model.ProvinceCountModel;
import com.air.health.instruction.entity.InstructionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

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
    @Select("SELECT SUBSTRING_INDEX(ins_address, '-', 1) AS name, COUNT(*) AS num FROM tb_ins GROUP BY name")
    List<ProvinceCountModel> getInsCountByProvince();

    @Select("SELECT COUNT(*) " +
            "FROM tb_ins " +
            "WHERE user_registerTime BETWEEN #{startDate} AND #{endDate}")
    Integer getCountsByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
