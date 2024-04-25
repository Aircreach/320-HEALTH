package com.air.health.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Title: MedicalRecordEntity
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.entity
 * @Date 2024/4/4 13:37
 * @description:
 */


@Data
@TableName("tb_medicalRecord")
public class MedicalRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 病历id
     */
    @TableId(value = "medicalRecord_id", type = IdType.ASSIGN_ID)
    private Long medicalRecordId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 编辑者
     */
    @TableField(value = "modifiedBy", fill = FieldFill.INSERT_UPDATE)
    private Long modifiedBy;
    /**
     * 编辑日期
     */
    @TableField(value = "modifiedDate", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedDate;
    /**
     * 文件路径
     */
    @TableField("filepath")
    private String filepath;
}
