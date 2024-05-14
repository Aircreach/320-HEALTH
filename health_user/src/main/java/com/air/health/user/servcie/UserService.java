package com.air.health.user.servcie;

import com.air.health.common.model.PageModel;
import com.air.health.user.entity.UserEntity;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

/**
 * @Title: UserService
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.servcie
 * @Date 2024/1/22 14:16
 * @description:
 */
public interface UserService extends IService<UserEntity>, UserDetailsService {

    PageModel queryPage(Map<String, Object> params);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
