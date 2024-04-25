package com.air.health.common.exception;

import com.air.health.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Title: AirExceptionHandler
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.exception
 * @Date 2024/4/5 15:21
 * @description:
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class , Controller.class})
public class AirExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(AirException.class)
    public Result handleAirException(AirException err){
        Result result = new Result();
        result.put("code", err.getCode());
        result.put("msg", err.getMessage());

        return result;
    }
    /**
     * SQL 处理异常
     * @param exception
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result handleSQLException(SQLIntegrityConstraintViolationException exception){
        String exceptionMsg = exception.getMessage();
        log.error(exceptionMsg);
        //违反数据库唯一约束条件 ==> 唯一约束属性词条冲突
        if (exceptionMsg.contains("Duplicate entry")){
            //以 " " 分隔 , 获取 String 存入数组
            String[] split = exceptionMsg.split(" ");
            return Result.error("Id  " + split[2] + "  已存在");
        }
        return Result.error("未知错误");
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNoFoundException(Exception err) {
        log.error (err.getMessage(), err);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException err){
        log.error (err.getMessage(), err);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result handleAuthorizationException(AuthenticationException err){
        log.error (err.getMessage(), err);
        return Result.error("权限不足，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception err){
        log.error (err.getMessage(), err);
        return Result.error();
    }
    
}
