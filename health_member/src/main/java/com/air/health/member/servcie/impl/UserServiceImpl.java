package com.air.health.member.servcie.impl;

import com.air.health.common.util.PageUtils;
import com.air.health.common.util.Query;
import com.air.health.member.entity.UserEntity;
import com.air.health.member.dao.UserDao;
import com.air.health.member.servcie.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title: UserServiceImpl
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.servcie.impl
 * @Date 2024/4/4 15:50
 * @description:
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }
}