package com.air.health.instruction.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: BerthStatus
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.model
 * @Date 2024/4/29 20:02
 * @description:
 */
@AllArgsConstructor
public enum BerthStatus {
    FREE(1, "空闲"),
    INUSE(2, "繁忙"),
    REPAIR(3, "修理中..."),
    BREAK(4, "损坏");


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
