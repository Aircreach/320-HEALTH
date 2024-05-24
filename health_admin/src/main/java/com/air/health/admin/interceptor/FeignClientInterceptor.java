package com.air.health.admin.interceptor;

import com.air.health.common.util.Constants;
import com.air.health.common.util.TokenProvider;
import com.air.health.common.util.UUIDUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TokenProvider tokenProvider;

    @Value("${app.jwt.feign-header}")
    String header;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String uuid = UUIDUtil.generateDigitUUID(9);
        String token = tokenProvider.generate(Constants.TOKEN_FEIGN, uuid, 5000L);
        redisTemplate.opsForValue().set(String.format(Constants.REDIS_KEY_PREFIX_TOKEN_FEIGN, uuid), uuid, 5000, TimeUnit.MILLISECONDS);
        requestTemplate.header(header, token);
    }
}
