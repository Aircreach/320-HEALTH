package com.air.health.admin.filter;

import com.air.health.common.model.AirException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter implements Filter {

    /**
     * session-key-验证码存储
     */
    @Value("${app.common.validate}")
    private String VALIDATE = "validate";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取请求路径
        String requestURI = httpRequest.getRequestURI();

        if (requestURI.equals("/user/login")) {
            String validate = (String) redisTemplate.opsForValue().get(String.format(VALIDATE + "%s", request.getParameter("uuid")));
            if (validate == null || !validate.equals(request.getParameter("kaptcha").toString())) {
                throw new AirException("ERROR => 验证码错误");
            }
            chain.doFilter(request, response);
        } else {
            // 对其他请求直接放行，不进行验证码校验
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 销毁操作
    }
}
