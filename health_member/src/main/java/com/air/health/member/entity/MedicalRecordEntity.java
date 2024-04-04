package com.air.health.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
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
public class MedicalRecordEntity {
    @TableId
    private Integer medicalRecordId;

    @TableField
    private Integer userId;

    @TableField
    private Integer modifiedBy;

    @TableField
    private LocalDateTime modifiedDate;

    @TableField
    private String filepath;
}
