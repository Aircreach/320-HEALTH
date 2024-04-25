package com.air.health.common.util;

import com.air.health.common.filter.SQLFilter;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @Title: Query
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.util
 * @Date 2024/4/4 15:53
 * @description:
 */

public class PageUtil<T> {

    public static <T> IPage<T> getPage(Map<String, Object> params) {
        return getPage(params, null, false);
    }

    public static <T> IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if(params.get(Constants.PAGE) != null){
            curPage = Long.parseLong(params.get(Constants.PAGE).toString());
        }
        if(params.get(Constants.LIMIT) != null){
            limit = Long.parseLong(params.get(Constants.LIMIT).toString());
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
//        String orderField = SQLFilter.sqlInject((String)params.get(Constants.FIELD));
//        String order = (String)params.get(Constants.ORDER);

//        //前端字段排序
//        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
//            if(Constants.ASC.equalsIgnoreCase(order)) {
//                return  page.addOrder(OrderItem.asc(orderField));
//            } else {
//                return page.addOrder(OrderItem.desc(orderField));
//            }
//        }
//
//        //没有排序字段，则不排序
//        if(StringUtils.isBlank(defaultOrderField)){
//            return page;
//        }
//
//        //默认排序
//        if(isAsc) {
//            page.addOrder(OrderItem.asc(defaultOrderField));
//        }else {
//            page.addOrder(OrderItem.desc(defaultOrderField));
//        }

        return page;
    }

    public static <T> QueryWrapper<T> getQueryWrapper(List<Map> data, Class<T> entityClass) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        if (!data.isEmpty()) {
            data.forEach(item -> {
                try {
                    String type = (String) item.get(Constants.METHOD);
                    String columnName = "";
                    // 获取属性对应的 Field 对象
                    Field field = entityClass.getDeclaredField((String) item.get(Constants.FIELD));
                    // 设置可以访问私有字段
                    field.setAccessible(true);
                    // 获取属性上的 @TableField 注解
                    TableField tableFieldAnnotation = field.getAnnotation(TableField.class);
                    switch (type) {
                        case Constants.EQUAL:
                            if (tableFieldAnnotation != null) {
                                columnName = tableFieldAnnotation.value();
                                // 添加查询条件
                                queryWrapper.eq(columnName, item.get("value"));
                            } else {
                                // 获取属性的值
                                queryWrapper.eq((String) item.get(Constants.FIELD), item.get("value"));
                            }
                            break;
                        case Constants.LIKE:
                            if (tableFieldAnnotation != null) {
                                columnName = tableFieldAnnotation.value();
                                // 添加查询条件
                                queryWrapper.like(columnName, item.get("value"));
                            } else {
                                // 获取属性的值
                                queryWrapper.like((String) item.get(Constants.FIELD), item.get("value"));
                            }
                            break;
                        case Constants.ORDER:
                            if (tableFieldAnnotation != null) {
                                columnName = tableFieldAnnotation.value();
                                // 添加查询条件
                                if ((item.get("value")).equals(Constants.ASC)) {
                                    queryWrapper.orderByAsc(columnName);
                                } else if ((item.get("value")).equals(Constants.DESC)) {
                                    queryWrapper.orderByDesc(columnName);
                                }
                            } else {
                                if ((item.get("value")).equals(Constants.ASC)) {
                                    queryWrapper.orderByAsc((String) item.get(Constants.FIELD));
                                } else if ((item.get("value")).equals(Constants.DESC)) {
                                    queryWrapper.orderByDesc((String) item.get(Constants.FIELD));
                                }
                            }
                            break;
                    }

                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return  queryWrapper;
    }
}

