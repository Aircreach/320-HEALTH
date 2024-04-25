package com.air.health.common.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Title: KaptchUtil
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.util
 * @Date 2024/4/8 14:13
 * @description:
 */
@Configuration
public class KaptchaUtil {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "110");   // 验证码图片宽度
        properties.setProperty("kaptcha.image.height", "36");   // 验证码图片高度
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");   // 纯数字验证码
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        properties.setProperty("kaptcha.noise.color", "black");
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");  // 干扰线
        properties.setProperty("kaptcha.background.clear.from", "232,240,254"); // 渐变色
        properties.setProperty("kaptcha.background.clear.to", "232,240,254");
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
