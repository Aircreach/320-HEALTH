package com.air.health.admin.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @Title: AdvisoryStatus
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.admin.model
 * @Date 2024/5/18 17:33
 * @description:
 */
@AllArgsConstructor
public enum AdvisoryStatus {

    FINISHED(0, "已完成"),
    UNFINISH(1, "未完成"),
    CANCEL(2, "已取消");

    @EnumValue
    private int value;

    @JsonValue
    private String content;

    public int getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }
}
