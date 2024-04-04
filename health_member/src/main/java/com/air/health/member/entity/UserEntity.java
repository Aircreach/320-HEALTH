package com.air.health.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

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

    /**
     * 用户id
     */
    @TableId
    private Long userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户全名
     */
    private String fullName;
    /**
     * 用户性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date date;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 身份证号
     */
    private BigInteger identity;
    /**
     * 手机号码
     */
    private BigInteger phone;
    /**
     * 紧急联系方式
     */
    private BigInteger eci;
    /**
     * 家庭住址
     */
    private String address;
    /**
     * 病历id
     */
    private Integer mrId;
    /**
     * 账号状态
     */
    private boolean isAlive;
}
