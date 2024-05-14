package com.air.health.instruction.entity.dto;

import com.air.health.instruction.entity.DepartEntity;

import java.util.ArrayList;

/**
 * @Title: DepartDto
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.entity.dto
 * @Date 2024/4/24 19:34
 * @description:
 */
public class DepartDto extends DepartEntity {

    private ArrayList<DepartDto> children;

    public ArrayList<DepartDto> getChildren() {
        return this.children;
    }

    public void setChildren(ArrayList<DepartDto> children) {
        this.children = children;
    }
}
