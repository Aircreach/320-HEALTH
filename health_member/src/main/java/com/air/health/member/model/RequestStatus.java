package com.air.health.member.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @Title: RequestType
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.model
 * @Date 2024/5/23 18:36
 * @description:
 */
@AllArgsConstructor
public enum RequestStatus {

    UNKNOWN(0, "未知状态"),
    DEVICE(1, "审核中"),
    RESOURCE(2, "已批准"),
    REIMBURSE(3, "已拒绝");

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
