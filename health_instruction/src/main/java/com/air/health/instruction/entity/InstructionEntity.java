package com.air.health.instruction.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
public class InstructionEntity {
    @TableId
    private Integer insId;

    @TableField
    private String insName;

    @TableField
    private String address;

    @TableField
    private String insDesc;

    @TableField(fill = FieldFill.INSERT)
    private Boolean status;
}
