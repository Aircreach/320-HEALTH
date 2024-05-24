package com.air.health.user.servcie.impl;

import com.air.health.common.util.PageUtil;
import com.air.health.user.dao.EvaluationDao;
import com.air.health.user.entity.EvaluationEntity;
import com.air.health.user.entity.UserEntity;
import com.air.health.user.servcie.EvaluationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import com.air.health.common.model.PageModel;



@Service("evaluationService")
public class EvaluationServiceImpl extends ServiceImpl<EvaluationDao, EvaluationEntity> implements EvaluationService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<EvaluationEntity> queryWrapper = new QueryWrapper<EvaluationEntity>();
        if (params.get("extra") != null) {
            ArrayList<Map> temp = (ArrayList<Map>) params.get("extra");
            Class<EvaluationEntity> entityClass = EvaluationEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<EvaluationEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );

        return new PageModel(page);
    }

}