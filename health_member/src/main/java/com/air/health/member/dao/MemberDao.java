package com.air.health.member.dao;

import com.air.health.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-23 11:01:02
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
}
