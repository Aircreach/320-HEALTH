package com.air.health.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: WSMsgModel
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.model
 * @Date 2024/5/21 13:47
 * @description:
 */
@Data
@AllArgsConstructor
public class WSMsgModel {

    private WSType type;

    private Object data;
}
