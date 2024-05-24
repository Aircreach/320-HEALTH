package com.air.health.instruction.service.impl;

import com.air.health.common.model.PageModel;
import com.air.health.common.util.PageUtil;
import com.air.health.instruction.dao.MedicineDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.air.health.instruction.entity.MedicineEntity;
import com.air.health.instruction.service.MedicineService;


@Service("medicineService")
public class MedicineServiceImpl extends ServiceImpl<MedicineDao, MedicineEntity> implements MedicineService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<MedicineEntity> queryWrapper = new QueryWrapper<MedicineEntity>();
        if (params.get("extra") != null) {
            ArrayList<Map> temp = (ArrayList<Map>) params.get("extra");
            Class<MedicineEntity> entityClass = MedicineEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<MedicineEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );

        return new PageModel(page);
    }

}