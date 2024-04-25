package com.air.health.user.servcie.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.user.entity.UserEntity;
import com.air.health.user.dao.UserDao;
import com.air.health.user.servcie.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                PageUtil.getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageModel(page);
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        UserEntity user = userDao.selectOne(queryWrapper);
        //查询不到该用户信息抛异常
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        //封装成userEntity返回
        return user;
    }
}