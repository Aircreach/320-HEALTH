package com.air.health.admin.entity;

import com.air.health.common.handler.EncodeTypeHandler;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @Title: AdminEntity
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.admin.entity
 * @Date 2024/5/17 21:17
 * @description:
 */
@Data
@TableName("tb_admin")
public class AdminEntity implements Serializable, UserDetails {

    @TableId(value = "admin_id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long adminId;

    @TableField("admin_name")
    private String adminName;

    @TableField(value = "admin_password", typeHandler = EncodeTypeHandler.class)
    private String password;

    @TableField(value = "admin_createdDate", fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return adminName;
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
