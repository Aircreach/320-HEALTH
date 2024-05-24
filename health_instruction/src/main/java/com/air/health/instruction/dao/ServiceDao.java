package com.air.health.instruction.dao;

import com.air.health.instruction.entity.ServiceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:38:13
 */
@Mapper
public interface ServiceDao extends BaseMapper<ServiceEntity> {
    @Select("SELECT service_id FROM tb_service WHERE ins_id = #{insId}")
    List<Long> selectAllIds(Long insId);
}
