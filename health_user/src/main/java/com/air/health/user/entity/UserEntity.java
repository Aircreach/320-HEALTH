package com.air.health.user.entity;

import com.air.health.common.handler.EncodeTypeHandler;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @Title: user
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.entity
 * @Date 2024/1/22 13:37
 * @description:
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long userId;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String username;
    /**
     * 密码
     */
    @TableField(value = "user_password", typeHandler = EncodeTypeHandler.class)
    @JsonIgnore()
    private String password;
    /**
     * 全名
     */
    @TableField("user_fullName")
    private String fullName;
    /**
     * 性别
     */
    @TableField("user_gender")
    private Integer gender;
    /**
     * 出生日期
     */
    @TableField("user_dateOfBirth")
    private LocalDateTime dateofbirth;
    /**
     * 年龄
     */
    @TableField("user_age")
    private Integer age;
    /**
     * 身份证号
     */
    @TableField("user_identity")
    private String identity;
    /**
     * 电话号码
     */
    @TableField("user_phoneNumber")
    private Long phoneNumber;
    /**
     * 紧急联系方式
     */
    @TableField("user_emergencyContact")
    private Long emergencyContact;
    /**
     * 地址
     */
    @TableField("user_address")
    private String address;
    /**
     * 注册时间
     */
    @TableField(value = "user_registerTime", fill = FieldFill.INSERT)
    private LocalDateTime registerTime;
    /**
     *
     */
    @TableField(value = "user_loginTime", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime loginTime;
    /**
     * 状态
     */
    @TableField("user_status")
    private Integer status;
    /**
     * 所属机构
     */
    @TableField("ins_id")
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long insId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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
