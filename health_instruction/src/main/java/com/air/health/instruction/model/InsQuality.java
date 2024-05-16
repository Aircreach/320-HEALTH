package com.air.health.instruction.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @Title: InsQuality
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.model
 * @Date 2024/5/14 12:51
 * @description:
 */
@AllArgsConstructor
public enum InsQuality {
    HOSPITAL(1, "医院"),
    COMMUNITY(1, "养老社区"),
    APARTMENT(3, "老年公寓"),
    ORGANIZATION(4, "综合性养老机构");


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

    @JsonCreator
    public static InsQuality fromContent(String content) {
        for (InsQuality insQuality : InsQuality.values()) {
            if (insQuality.getContent().equals(content)) {
                return insQuality;
            }
        }
        throw new IllegalArgumentException("Unknown content: " + content);
    }
}
