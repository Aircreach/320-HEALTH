package com.air.health.common.model;

import java.util.ArrayList;

/**
 * @Title: TreeModel
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.model
 * @Date 2024/4/24 19:34
 * @description:
 */
public interface TreeModel<T> {
    ArrayList<? extends TreeModel<T>> getChildren();
    void setChildren(ArrayList<? extends TreeModel<T>> children);
}
