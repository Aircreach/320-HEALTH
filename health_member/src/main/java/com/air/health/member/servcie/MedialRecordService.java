package com.air.health.member.servcie;

import com.air.health.common.util.PageUtils;
import com.air.health.member.entity.MedicalRecordEntity;
import com.air.health.member.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Title: MedialRecordService
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.servcie
 * @Date 2024/4/4 21:33
 * @description:
 */
public interface MedialRecordService extends IService<MedicalRecordEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
