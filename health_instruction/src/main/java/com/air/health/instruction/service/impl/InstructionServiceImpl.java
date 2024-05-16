package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.instruction.dao.InstructionDao;
import com.air.health.instruction.entity.DepartEntity;
import com.air.health.instruction.entity.InstructionEntity;
import com.air.health.instruction.service.InstructionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Title: InstructionServiceImpl
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service.impl
 * @Date 2024/4/4 16:53
 * @description:
 */
@Service("instructionService")
public class InstructionServiceImpl extends ServiceImpl<InstructionDao, InstructionEntity> implements InstructionService {

    @Autowired
    InstructionDao instructionDao;

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<InstructionEntity> queryWrapper = new QueryWrapper<InstructionEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<InstructionEntity> entityClass = InstructionEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<InstructionEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<InstructionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InstructionEntity::getInstructionName, username);
        InstructionEntity member = instructionDao.selectOne(queryWrapper);
        //查询不到该用户信息抛异常
        if(member == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        //封装成userEntity返回
        return member;
    }
}
