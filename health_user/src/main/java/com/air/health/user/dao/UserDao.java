package com.air.health.user.dao;

import com.air.health.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
}
