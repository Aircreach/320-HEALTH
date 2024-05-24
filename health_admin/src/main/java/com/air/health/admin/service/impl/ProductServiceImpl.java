package com.air.health.admin.service.impl;

import com.air.health.admin.dao.ProductDao;
import com.air.health.admin.entity.AdvisoryEntity;
import com.air.health.admin.entity.ProductEntity;
import com.air.health.admin.service.ProductService;
import com.air.health.common.util.PageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.air.health.common.model.PageModel;


@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<ProductEntity>();
        if (params.get("extra") != null) {
            ArrayList temp = (ArrayList) params.get("extra");
            Class<ProductEntity> entityClass = ProductEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<ProductEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );
        return new PageModel(page);
    }

}