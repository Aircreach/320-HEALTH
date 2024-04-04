package com.air.health.member.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id")
    private Long id;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String name;
    /**
     * 密码
     */
    @TableField("user_password")
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
    @TableField("user_registerTime")
    private LocalDateTime registerTime;
    /**
     *
     */
    @TableField("user_loginTime")
    private LocalDateTime loginTime;
    /**
     *
     */
    @TableField("user_status")
    private Integer status;
}
