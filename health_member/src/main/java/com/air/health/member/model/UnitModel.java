package com.air.health.member.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UnitModel {
    // 列出每个部门的枚举实例
    NEUROLOGY(1, "内科"),
    SURGERY(2, "外科"),
    PEDIATRICS(3, "儿科"),
    OBSTETRICS_GYNECOLOGY(4, "妇产科"),
    PSYCHIATRY(5, "神经科"),
    PSYCHOLOGY(6, "心理科"),
    OPHTHALMOLOGY(7, "眼科"),
    OTOLARYNGOLOGY(8, "耳鼻喉科"),
    DENTISTRY(9, "口腔科"),
    DERMATOLOGY(10, "皮肤科"),
    ONCOLOGY(11, "肿瘤科");

    @EnumValue
    private final int value;

    @JsonValue
    private final String content;


    public int getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }
}
