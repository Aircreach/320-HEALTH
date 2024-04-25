package com.air.health.common.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Title: KaptchaController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.controller
 * @Date 2024/4/8 14:30
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(("/kaptcha"))
public class KaptchaController {

    @Autowired
    DefaultKaptcha kaptcha;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * session-key-验证码存储
     */
    @Value("${app.common.validate}")
    private String VALIDATE = "validate";

    @RequestMapping("/get")
    public void getKaptcha(HttpSession session, HttpServletResponse response, @RequestParam("timestamp") long timestamp) throws IOException {
        // 设置响应内容类型为图片
        response.setContentType("image/jpeg");
        // 禁用缓存
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        // 构造验证码
        String validate = kaptcha.createText();
        session.setAttribute(VALIDATE, validate);
        redisTemplate.opsForValue().set(
                String.format(VALIDATE + "%s", timestamp),
                validate,
                1,
                TimeUnit.MINUTES
        );
        log.info("================{}", String.format(VALIDATE + "%s", timestamp));
        BufferedImage buffer = kaptcha.createImage(validate);
        //输出流 ==> 写回浏览器(客户端)
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(buffer, "jpg", outputStream);
        outputStream.flush();
//        return Result.success().put("validate", timestamp);
    }
}
