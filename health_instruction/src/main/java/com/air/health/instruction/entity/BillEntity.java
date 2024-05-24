package com.air.health.instruction.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Title: BillEntity
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.entity
 * @Date 2024/4/4 21:58
 * @description:
 */
@Data
@TableName("tb_bill")
public class BillEntity implements Serializable {
    @TableId(value = "bill_id")
    private Long id;

    @TableField("bill_desc")
    private String description;

    @TableField("bill_amount")
    private Double amount;

    @TableField(value = "bill_createdDate", fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    @TableField("bill_paid")
    private Boolean paid;

    @TableField("bill_paidDate")
    private LocalDateTime paidDate;

    @TableField("ins_id")
    private Long insId;
}
