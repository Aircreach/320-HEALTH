package com.air.health.common.model;

/**
 * @Title: AirException
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.util
 * @Date 2024/4/4 15:32
 * @description:
 */
public class AirException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public AirException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AirException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public AirException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public AirException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
