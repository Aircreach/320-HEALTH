package com.air.health.instruction.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @Title: ServiceType
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.model
 * @Date 2024/5/16 16:15
 * @description:
 */
@AllArgsConstructor
public enum ServiceType {
    UNKNOWN(0, "未知类型"),
    NURSE(1, "护理服务"),
    EXAMINATION(2, "体检服务"),
    TREAT(3, "医疗服务");

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
