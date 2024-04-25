package com.air.health.common.util;

/**
 * @Title: Constabts
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.oa.common.model
 * @Date 2024/4/18 12:59
 * @description:
 */
public class Constants {

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
    // eq
    public static final String EQUAL = "equal";
    // like
    public static final String LIKE = "like";
    // order
    public static final String ORDER = "order";
    // 升序
    public static final String ASC = "asc";
    // 降序
    public static final String DESC = "desc";
    // 额外数据
    public static final String EXTRA = "extra";

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
    public static final String REDIS_KEY_PREFIX_TOKEN = "TOKEN-%s";

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
}

