package com.air.health.admin.entity;

import com.air.health.admin.model.AdvisoryStatus;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Title: AdvisoryEntity
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.admin.entity
 * @Date 2024/5/18 17:30
 * @description:
 */
@Data
@TableName("sys_advisory")
public class AdvisoryEntity implements Serializable {
    @TableId(value = "advisory_id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long advisoryId;

    @TableField("send_id")
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long adminId;

    @TableField("advisory_ic")
    private String icon;

    @TableField("advisory_title")
    private String title;

    @TableField("advisory_content")
    private String content;

    @TableField(value = "createdDate", fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    @TableField("publishDate")
    private LocalDateTime publishDate;

    @TableField("status")
    private AdvisoryStatus status;
}
