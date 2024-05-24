package com.air.health.admin.filter;

import com.air.health.admin.entity.AdminEntity;
import com.air.health.common.model.AirException;
import com.air.health.common.util.Constants;
import com.air.health.common.util.TokenProvider;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    TokenProvider tokenProvider;

    @Value("${app.jwt.feign-header}")
    String header;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, JwtException {
        if ("/admin/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response); // 放行登录接口
            return;
        }

        // 跨域请求认证
        if (request.getHeader(header) != null) {
            String feign_token = request.getHeader(header);
            Claims feign_claims = tokenProvider.parseToken(Constants.TOKEN_FEIGN, feign_token);
            if (feign_claims == null){
                throw new JwtException("token 异常");
            }
            //判断jwt是否过期
            if (tokenProvider.isExpired(feign_claims)){
                throw new JwtException("token 过期");
            }
            String key = feign_claims.getSubject();
            String feign_data = (String) redisTemplate.opsForValue().get(String.format(Constants.REDIS_KEY_PREFIX_TOKEN_FEIGN, key));
            if (feign_data == null) {
                throw new AirException("请求认证异常, 未知原因");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(key, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getHeader(tokenProvider.getDefaultHeader()) != null) {
            String token = request.getHeader(tokenProvider.getDefaultHeader());
            Claims claims = tokenProvider.parseToken(Constants.TOKEN_ADMIN, token);
            if (claims == null){
                throw new JwtException("token 异常");
            }
            //判断jwt是否过期
            if (tokenProvider.isExpired(claims)){
                throw new JwtException("token 过期");
            }
            String data = (String) redisTemplate.opsForValue().get(String.format(Constants.REDIS_KEY_PREFIX_TOKEN_ADMIN, claims.getSubject()));
            if (data == null) {
                throw new AirException("登陆异常, 请重新登录");
            } else {
                AdminEntity admin = JSON.parseObject(data, AdminEntity.class);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin.getUsername(), null, admin.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            }
        } else {
            throw new JwtException("token 异常");
        }
    }
}