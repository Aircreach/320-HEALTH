package com.air.health.user.servcie.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.user.dao.MedicalRecordDao;
import com.air.health.user.entity.MedicalRecordEntity;
import com.air.health.user.servcie.MedialRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title: MedicalRecordServiceimpl
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.servcie.impl
 * @Date 2024/4/4 21:34
 * @description:
 */
@Service("medicalRecordService")
public class MedicalRecordServiceImpl extends ServiceImpl<MedicalRecordDao, MedicalRecordEntity> implements MedialRecordService {
    @Override
    public PageModel queryPage(Map<String, Object> params) {
        IPage<MedicalRecordEntity> page = this.page(
                PageUtil.getPage(params),
                new QueryWrapper<MedicalRecordEntity>()
        );

        return new PageModel(page);
    }
}
