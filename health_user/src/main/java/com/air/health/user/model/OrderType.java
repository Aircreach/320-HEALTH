package com.air.health.user.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderType {
    UNKNOWN(0, "未知类型"),
    NURSE(1, "护理服务"),
    EXAMINATION(2, "体检服务"),
    TREAT(3, "医疗服务"),
    MERCHANDISE(4, "商品服务"),
    OCCUPANCY(5, "入住服务"),
    VIDEO(6, "远程医疗");

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