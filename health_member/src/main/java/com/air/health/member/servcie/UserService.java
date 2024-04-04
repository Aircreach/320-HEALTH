package com.air.health.member.servcie;

import com.air.health.common.util.PageUtils;
import com.air.health.member.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @Title: UserService
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.servcie
 * @Date 2024/1/22 14:16
 * @description:
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
