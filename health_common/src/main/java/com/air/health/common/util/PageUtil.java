package com.air.health.common.util;

import com.air.health.common.filter.SQLFilter;
import com.air.health.common.model.QueryModel;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class PageUtil<T> {

    public static <T> IPage<T> getPage(Map<String, Object> params) {
        return getPage(params, null, false);
    }

    public static <T> IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(Constants.PAGE) != null) {
            curPage = Long.parseLong(params.get(Constants.PAGE).toString());
        }
        if (params.get(Constants.LIMIT) != null) {
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
                    // 获取属性对应的 Field 对象
                    Field field = entityClass.getDeclaredField((String) item.get(Constants.FIELD));
                    // 设置可以访问私有字段
                    field.setAccessible(true);
                    // 获取属性上的 @TableField 注解
                    TableField tableFieldAnnotation = field.getAnnotation(TableField.class);
                    // 获取列名
                    String columnName = "";
                    if (tableFieldAnnotation != null) {
                        columnName = SQLFilter.sqlInject(tableFieldAnnotation.value());
                    } else {
                        columnName = (String) item.get(Constants.FIELD);
                    }

                    switch (type) {
                        case Constants.EQUAL:
                            // 获取属性的值
                            queryWrapper.eq(columnName, item.get(Constants.VALUE));
                            break;
                        case Constants.LIKE:
                            // 获取属性的值
                            queryWrapper.like(columnName, item.get(Constants.VALUE));
                            break;
                        case Constants.BETWEEN:
                            // 获取属性的值
                            queryWrapper.between(columnName, item.get(Constants.VALUE1), item.get(Constants.VALUE2));
                            break;
                        case Constants.GE:
                            // 获取属性的值
                            queryWrapper.ge(columnName, item.get(Constants.VALUE));
                            break;
                        case Constants.LE:
                            // 获取属性的值
                            queryWrapper.le(columnName, item.get(Constants.VALUE));
                            break;
                        case Constants.ORDER:
                            String value = SQLFilter.sqlInject((String) item.get(Constants.VALUE));
                            if (value.equals(Constants.ASC)) {
                                queryWrapper.orderByAsc(columnName);
                            } else if (value.equals(Constants.DESC)) {
                                queryWrapper.orderByDesc(columnName);
                            }
                            break;
                    }
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return queryWrapper;
    }
}

