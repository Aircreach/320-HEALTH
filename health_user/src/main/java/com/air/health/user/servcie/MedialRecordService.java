package com.air.health.user.servcie;

import com.air.health.common.model.PageModel;
import com.air.health.user.entity.MedicalRecordEntity;
import com.alibaba.fastjson.JSONObject;
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
    PageModel queryPage(Map<String, Object> params);
}
