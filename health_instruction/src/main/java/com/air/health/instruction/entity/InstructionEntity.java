package com.air.health.instruction.entity;

import com.air.health.common.handler.EncodeTypeHandler;
import com.air.health.common.util.AirPasswordEncoder;
import com.air.health.common.util.Constants;
import com.air.health.instruction.handler.JSONListTypeHandler;
import com.air.health.instruction.model.InsOperated;
import com.air.health.instruction.model.InsQuality;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.ibatis.type.EnumTypeHandler;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Title: InstructionEntity
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.entity
 * @Date 2024/4/4 13:35
 * @description:
 */
@Data
@TableName("tb_ins")
public class InstructionEntity implements Serializable, UserDetails {
    @TableId(value = "ins_id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long id;

    @TableField("ins_ic")
    private String icon;

    @TableField("ins_name")
    private String instructionName;

    @TableField(value = "ins_pwd", typeHandler = EncodeTypeHandler.class)
    private String password;

    @TableField("ins_address")
    private String address;

    @TableField("ins_score")
    private Integer score;

    @TableField("ins_evalNum")
    private Integer evalNum;

    @TableField("ins_desc")
    private String description;

    @TableField(value = "ins_quality")
    private InsQuality quality;

    @TableField(value = "ins_operated")
    private InsOperated operated;

    @TableField(value = "ins_status")
    private Constants.AccountStatus status;

    @TableField(value = "ins_label", typeHandler = JSONListTypeHandler.class)
    private List<String> label;

    @TableField(value = "ins_price")
    private Integer price;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return instructionName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
