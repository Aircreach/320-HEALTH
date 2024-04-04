package com.air.health.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableId(value = "medicalRecord_id")
    private Long medicalRecordId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 编辑者
     */
    @TableField("modifiedBy")
    private Long modifiedBy;
    /**
     * 编辑日期
     */
    @TableField("modifiedDate")
    private LocalDateTime modifiedDate;
    /**
     * 文件路径
     */
    @TableField("filepath")
    private String filepath;
}
