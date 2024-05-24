package com.air.health.user.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class UserMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "registerTime", LocalDateTime.class, LocalDateTime.now(ZoneOffset.UTC));
        this.strictInsertFill(metaObject, "loginTime", LocalDateTime.class, LocalDateTime.now(ZoneOffset.UTC));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 这里可以定义更新操作时需要填充的字段，如果不需要可以不重写该方法
    }
}
