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
public enum RequestType {

    UNKNOWN(0, "未知类型"),
    DEVICE(1, "设备申请"),
    RESOURCE(2, "资源申请"),
    REIMBURSE(3, "报销申请"),
    VACATION(4, "请假申请");

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
