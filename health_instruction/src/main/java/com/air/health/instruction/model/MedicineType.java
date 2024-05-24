package com.air.health.instruction.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @Title: MedicineType
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.model
 * @Date 2024/5/16 16:11
 * @description:
 */
@AllArgsConstructor
public enum MedicineType {
    UNKNOWN(0, "未知类型"),
    PRESCRIPTION(1, "处方药"),
    UNPRESCRIPTION(2, "非处方药");

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
