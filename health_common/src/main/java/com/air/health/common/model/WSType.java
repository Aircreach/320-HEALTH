package com.air.health.common.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @Title: WSType
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.model
 * @Date 2024/5/21 13:48
 * @description:
 */
@AllArgsConstructor
public enum WSType {
    TEXT,
    OBJECT,
    MEDIA,
    ADVISORY,
    VIDEO,
    ERROR;
}
