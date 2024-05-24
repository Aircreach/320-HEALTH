package com.air.health.admin.service.impl;

import com.air.health.admin.dao.AdminDao;
import com.air.health.admin.entity.AdminEntity;
import com.air.health.admin.service.AdminService;
import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;


@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminEntity> implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<AdminEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<AdminEntity> entityClass = AdminEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<AdminEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<AdminEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminEntity::getAdminName, username);
        AdminEntity member = adminDao.selectOne(queryWrapper);
        //查询不到该用户信息抛异常
        if(member == null){
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }
        //封装成userEntity返回
        return member;
    }
}