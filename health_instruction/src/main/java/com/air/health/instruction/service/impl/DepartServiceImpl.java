package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.instruction.dao.DepartDao;
import com.air.health.instruction.entity.DepartEntity;
import com.air.health.instruction.entity.dto.DepartDto;
import com.air.health.instruction.service.DepartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Title: DepartServiceImpl
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.instruction.service.impl
 * @Date 2024/4/5 0:46
 * @description:
 */
@Service("departService")
public class DepartServiceImpl extends ServiceImpl<DepartDao, DepartEntity> implements DepartService {


    private DepartDto getDto(DepartEntity department) {
        DepartDto dto = new DepartDto();
        BeanUtils.copyProperties(department, dto);
        return dto;
    }

    private ArrayList<DepartDto> buildTree(DepartDto parentNode, ArrayList<DepartEntity> allDepartments) {
        Long parentId = parentNode.getDepartId();
        ArrayList<DepartDto> children = new ArrayList<>();
        for (DepartEntity department : allDepartments) {
            if (department.getSuperId() != null && department.getSuperId().equals(parentId)) {
                DepartDto childNode = getDto(department);
                childNode.setChildren(buildTree(childNode, allDepartments));
                children.add(childNode);
            }
        }
        return children;
    }

    @Override
    public ArrayList<DepartDto> queryTree(Long insId) {
        LambdaQueryWrapper<DepartEntity> queryWrapper = new LambdaQueryWrapper<DepartEntity>();
        queryWrapper.eq(DepartEntity::getInsId, insId).orderByAsc(DepartEntity::getDepartName);
        // 获取所有部门
        ArrayList<DepartEntity> allDepartments = (ArrayList<DepartEntity>) this.list(queryWrapper);

        // 构建树状结构
        ArrayList<DepartDto> departTree = new ArrayList<>();
        for (DepartEntity department : allDepartments) {
            // 如果是顶层部门，则直接添加到根节点列表
            if (department.getSuperId() == null || department.getSuperId() == -1) {
                DepartDto rootNode = getDto(department);
                rootNode.setChildren(buildTree(rootNode, allDepartments));
                departTree.add(rootNode);
            }
        }
        return departTree;
    }

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<DepartEntity> queryWrapper = new QueryWrapper<DepartEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<DepartEntity> entityClass = DepartEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<DepartEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }
}
