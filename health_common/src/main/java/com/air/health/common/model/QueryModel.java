package com.air.health.common.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: QueryModel
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.model
 * @Date 2024/4/25 19:34
 * @description:
 */
@Data
@AllArgsConstructor
public class QueryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    String type;
    String field;
    Object value;
}
