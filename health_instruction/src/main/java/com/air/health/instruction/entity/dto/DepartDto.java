package com.air.health.instruction.entity.dto;

import com.air.health.common.model.TreeModel;
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
public class DepartDto extends DepartEntity implements TreeModel<DepartDto> {

    ArrayList<DepartDto> children;

    @Override
    public ArrayList<DepartDto> getChildren() {
        return children;
    }

    @Override
    public void setChildren(ArrayList<? extends TreeModel<DepartDto>> children) {

    }
}
