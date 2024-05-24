package com.air.health.member.service.impl;

import com.air.health.common.util.PageUtil;
import com.air.health.member.dao.RequestDao;
import com.air.health.member.entity.MemberEntity;
import com.air.health.member.entity.RequestEntity;
import com.air.health.member.service.RequestService;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.air.health.common.model.PageModel;



@Service("requestService")
public class RequestServiceImpl extends ServiceImpl<RequestDao, RequestEntity> implements RequestService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<RequestEntity> queryWrapper = new QueryWrapper<RequestEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<RequestEntity> entityClass = RequestEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<RequestEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }

}