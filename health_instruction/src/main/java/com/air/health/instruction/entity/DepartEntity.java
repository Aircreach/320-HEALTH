package com.air.health.instruction.entity;

import com.air.health.instruction.handler.JSONListTypeHandler;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Title: DepartEntity
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.entity
 * @Date 2024/4/4 23:59
 * @description:
 */
@Data
@TableName("tb_depart")
public class DepartEntity {
    // 部门ID
    @TableId(value = "depart_id")
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long departId;

    // 部门名称
    @TableField("depart_name")
    private String departName;

    // 部门负责人id
    @TableField("depart_head")
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long departHead;

    // 部门职能
    @TableField(value = "depart_function", typeHandler = JSONListTypeHandler.class)
    private List<String> departFunc;

    // 部门简介
    @TableField("depart_description")
    private String description;

    // 部门地址
    @TableField("depart_address")
    private String address;

    // 联系信息
    @TableField("contact_info")
    private String contactInfo;

    // 创建日期
    @TableField(value = "created_date", fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    // 最后更新时间
    @TableField(value = "last_update", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastUpdate;

    // 部门预算
    @TableField("depart_budget")
    private Double budget;

    // 最大员工限制
    @TableField("max_employee_limit")
    private Integer maxEmployeeLimit;

    // 上级部门ID
    @TableField("super_id")
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long superId;

    // 部门级别
//    @TableField("depart_level")
//    private Integer level;

    // 机构ID
    @TableField("ins_id")
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long insId;
}
