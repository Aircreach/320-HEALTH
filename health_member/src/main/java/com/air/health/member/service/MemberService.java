package com.air.health.member.service;

import com.air.health.common.model.PageModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.air.health.member.entity.MemberEntity;
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
public interface MemberService extends IService<MemberEntity>, UserDetailsService {

    PageModel queryPage(Map<String, Object> params);

    Long countData(Map<String, Object> params);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

