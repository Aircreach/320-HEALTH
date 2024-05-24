package com.air.health.admin.service;

import com.air.health.admin.entity.AdminEntity;
import com.air.health.common.model.PageModel;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-23 11:01:02
 */
public interface AdminService extends IService<AdminEntity>, UserDetailsService {

    PageModel queryPage(Map<String, Object> params);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

