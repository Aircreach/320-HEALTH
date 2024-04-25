package com.air.health.common.util;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class TokenProvider {

    @Value("${app.jwt.secret}")
    private String JWTSecret;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWTExpirationInMs;

    @Value("${app.jwt.header}")
    private String TOKEN_HEADER;

    /**
     * generate token
     * @param subject
     * @return
     */
    public String generate(String subject) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWTExpirationInMs);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWTSecret)
                .compact();
    }

    public String parseToken(String token) throws IllegalArgumentException, ExpiredJwtException {
        Claims claims = Jwts.parser()
                .setSigningKey(JWTSecret)
                .parseClaimsJws(token)
                .getBody();

        // 判断jwt是否存在
        if (claims == null){
            throw new JwtException("token 异常");
        }
        // 判断jwt是否过期
        if (claims.getExpiration().before(new Date())){
            throw new JwtException("token 过期");
        }
        return claims.getSubject();
    }

    /**
     * validate token
     * @param authToken
     * @return
     */
    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWTSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            // log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            // log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            // log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            // log.error("JWT claims string is empty.");
        }
        return false;
    }

    /**
     * 获取过期时间
     */
    public Long getExpirationInMs() {
        return  this.JWTExpirationInMs;
    }

    /**
     * 获取请求头 key
     * @return
     */
    public String getHeader() {
        return this.TOKEN_HEADER;
    }

}

