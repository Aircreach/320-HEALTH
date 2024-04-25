package com.air.health.instruction.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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
public class InstructionEntity implements Serializable {
    @TableId(value = "ins_id")
    private Long id;

    @TableField("ins_name")
    private String name;

    @TableField("ins_address")
    private String address;

    @TableField("ins_score")
    private Integer score;

    @TableField("ins_evalNum")
    private  Integer evalNum;

    @TableField("ins_desc")
    private String description;

    @TableField(value = "ins_status", fill = FieldFill.INSERT)
    private Boolean status;
}
