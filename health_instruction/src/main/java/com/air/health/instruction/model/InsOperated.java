package com.air.health.instruction.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @Title: InsOperated
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.model
 * @Date 2024/5/14 12:55
 * @description:
 */
@AllArgsConstructor
public enum InsOperated {
    PUBLIC(1, "公办"),

    PRIVATE(2, "民办");


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
