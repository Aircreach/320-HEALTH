package com.air.health.member.servcie.impl;

import com.air.health.common.util.PageUtils;
import com.air.health.common.util.Query;
import com.air.health.member.dao.MedicalRecordDao;
import com.air.health.member.entity.MedicalRecordEntity;
import com.air.health.member.servcie.MedialRecordService;
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
@Service("medicalRecordServiceImpl")
public class MedicalRecordServiceImpl extends ServiceImpl<MedicalRecordDao, MedicalRecordEntity> implements MedialRecordService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MedicalRecordEntity> page = this.page(
                new Query<MedicalRecordEntity>().getPage(params),
                new QueryWrapper<MedicalRecordEntity>()
        );

        return new PageUtils(page);
    }
}
