package com.air.health.instruction.service;

import com.air.health.common.model.PageModel;
import com.air.health.common.model.ProvinceCountModel;
import com.air.health.instruction.entity.InstructionEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Title: InstructionService
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service
 * @Date 2024/4/4 16:40
 * @description:
 */
public interface InstructionService extends IService<InstructionEntity>, UserDetailsService {

    PageModel queryPage(Map<String, Object> params);

    List<ProvinceCountModel> provinceCount();

    Integer dateCount(LocalDateTime startDate, LocalDateTime endDate);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
