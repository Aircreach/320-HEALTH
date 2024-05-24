package com.air.health.instruction.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdDate", LocalDateTime.class, LocalDateTime.now(ZoneOffset.UTC));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 这里可以定义更新操作时需要填充的字段，如果不需要可以不重写该方法
        this.strictUpdateFill(metaObject, "lastUpdate", LocalDateTime.class, LocalDateTime.now(ZoneOffset.UTC));
//        this.strictUpdateFill(metaObject, "departFunc" , List.class, new ArrayList<String>());
    }
}
