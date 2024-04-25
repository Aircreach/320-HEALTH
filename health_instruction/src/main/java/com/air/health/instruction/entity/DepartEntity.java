package com.air.health.instruction.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

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
    private Long departId;

    // 部门名称
    @TableField("depart_name")
    private String departName;

    // 部门负责人id
    @TableField("depart_head")
    private Long departHead;

    // 员工数量
    @TableField("number_of_employees")
    private Integer numberOfEmployees;

    // 部门职能
    @TableField("depart_function")
    private String departFunc;

    // 部门地址
    @TableField("depart_address")
    private String address;

    // 联系信息
    @TableField("contact_info")
    private String contactInfo;

    // 创建日期
    @TableField("created_date")
    private LocalDateTime createdDate;

    // 最后更新时间
    @TableField("last_update")
    private LocalDateTime lastUpdate;

    // 部门预算
    @TableField("depart_budget")
    private Double budget;

    // 最大员工限制
    @TableField("max_employee_limit")
    private Integer maxEmployeeLimit;

    // 上级部门ID
    @TableField("super_id")
    private Long superId;

    // 部门级别
    @TableField("depart_level")
    private Integer level;

    // 机构ID
    @TableField("ins_id")
    private Long insId;
}
