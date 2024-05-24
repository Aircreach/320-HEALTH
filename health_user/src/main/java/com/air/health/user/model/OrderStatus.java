package com.air.health.user.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @Title: OrderStatus
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.user.model
 * @Date 2024/5/16 16:19
 * @description:
 */
@AllArgsConstructor
public enum OrderStatus {

    UNKNOWN(0, "未知"),
    COMPLETED(1, "已完成"),
    PENDING(2, "待完成"),
    PAYED(3, "未支付"),
    CANCELED(4, "已取消");

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
