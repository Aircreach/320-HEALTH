package com.air.health.common.util;

import lombok.AllArgsConstructor;

/**
 * @Title: Constabts
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.oa.common.model
 * @Date 2024/4/18 12:59
 * @description:
 */
public class Constants {

    /** COMMON **/
    // feign token type

    public static final int TOKEN_FEIGN = 0;
    // user token type
    public static final int TOKEN_USER = 1;
    // member token type
    public static final int TOKEN_MEMBER = 2;
    // instruction token type
    public static final int TOKEN_INS = 3;
    // admin token type
    public static final int TOKEN_ADMIN = 4;

    /** DATABASE **/
    // 超级管理员ID
    public static final int SUPER_ADMIN = 1;
    // 当前页码
    public static final String PAGE = "page";
    // 每页显示记录数
    public static final String LIMIT = "limit";
    // 查询方法
    public static final String METHOD = "type";
    // 字段
    public static final String FIELD = "field";
    // value
    public static final String VALUE = "value";
    public static final String VALUE1 = "value1";
    public static final String VALUE2 = "value2";
    // eq
    public static final String EQUAL = "equal";
    // like
    public static final String LIKE = "like";
    // between
    public static final String BETWEEN = "between";
    // in
    public static final String IN = "in";
    // ge
    public static final String GE = "ge";
    // le
    public static final String LE = "le";
    // order
    public static final String ORDER = "order";
    // 升序
    public static final String ASC = "asc";
    // 降序
    public static final String DESC = "desc";

    // 菜单类型
    public enum MenuType {
        // 目录
        CATALOG(0),
        // 菜单
        MENU(1),
        // 按钮
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /** redis 常量 **/
    public static final String REDIS_KEY_PREFIX_TOKEN_USER = "TOKEN-USER-%s";
    public static final String REDIS_KEY_PREFIX_TOKEN_MEMBER = "TOKEN-MEMBER-%s";
    public static final String REDIS_KEY_PREFIX_TOKEN_INS = "TOKEN-INS-%s";
    public static final String REDIS_KEY_PREFIX_TOKEN_ADMIN = "TOKEN-ADMIN-%s";
    public static final String REDIS_KEY_PREFIX_TOKEN_FEIGN = "TOKEN-FEIGN-%s";

    public static final String REDIS_KEY_PREFIX_SCHEDULE_ADVISORY = "SCHEDULE-ADVISORY";
    /** 定时任务 **/
    // 定时任务状态
    public enum ScheduleStatus {
        // 正常
        NORMAL(0),
        // 暂停
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /** 文件分类 **/
    @AllArgsConstructor
    public enum FilePath {
        AVATAR("/avatar"),
        COMMON("/common");

        String path;
    }

    /** 账号状态 **/
    public enum AccountStatus {
        // 正常
        NORMAL,
        // 停用
        DISABLED,
        // 废弃
        ABANDON;
    }
}

