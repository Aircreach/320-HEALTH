package com.air.health.member.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.air.health.member.dao.MemberDao;
import com.air.health.member.entity.MemberEntity;
import com.air.health.member.service.MemberService;


@Slf4j
@Service("memberService" )
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    MemberDao memberDao;

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<MemberEntity> queryWrapper = new QueryWrapper<MemberEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<MemberEntity> entityClass = MemberEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<MemberEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }

    @Override
    public Long countData(Map<String, Object> params) {
        QueryWrapper<MemberEntity> queryWrapper = new QueryWrapper<MemberEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<MemberEntity> entityClass = MemberEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        Long num = this.count(queryWrapper);
        return num;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<MemberEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MemberEntity::getMemberName, username);
        MemberEntity member = memberDao.selectOne(queryWrapper);
        //查询不到该用户信息抛异常
        if(member == null){
            throw new UsernameNotFoundException("Member not found with username: " + username);
        }
        //封装成userEntity返回
        return member;
    }
}