package com.air.health.user.dao;

import com.air.health.common.model.ProvinceCountModel;
import com.air.health.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Title: UserDao
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.dao
 * @Date 2024/4/4 13:43
 * @description:
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    @Select("SELECT SUBSTRING_INDEX(user_address, '-', 1) AS name, COUNT(*) AS num FROM tb_user GROUP BY name")
    List<ProvinceCountModel> getCountByProvince();

    @Select("SELECT COUNT(*) " +
            "FROM tb_user " +
            "WHERE user_registerTime BETWEEN #{startDate} AND #{endDate}")
    Integer getCountsByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
