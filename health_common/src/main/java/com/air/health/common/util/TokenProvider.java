package com.air.health.common.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class TokenProvider {

    @Value("${app.jwt.secret.user}")
    private String JWTSecret_User;

    @Value("${app.jwt.secret.member}")
    private String JWTSecret_Member;

    @Value("${app.jwt.secret.instruction}")
    private String JWTSecret_Ins;

    @Value("${app.jwt.secret.admin}")
    private String JWTSecret_Admin;

    @Value("${app.jwt.secret.feign}")
    private String JWTSecret_Feign;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWTExpirationInMs;

    @Value("${app.jwt.header}")
    private String TOKEN_HEADER;

    /**
     * generate token
     *
     * @param subject
     * @return
     */
    public String generate(int type, String subject) {
        return generate(type, subject, JWTExpirationInMs);
    }

    public String generate(int type, String subject, Long expiryTime) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiryTime);
        String secret = "";
        switch (type) {
            case Constants.TOKEN_USER -> secret = JWTSecret_User;
            case Constants.TOKEN_MEMBER -> secret = JWTSecret_Member;
            case Constants.TOKEN_INS -> secret = JWTSecret_Ins;
            case Constants.TOKEN_ADMIN -> secret = JWTSecret_Admin;
            case Constants.TOKEN_FEIGN -> secret = JWTSecret_Feign;
        }
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims parseToken(int type, String token) throws JwtException {
        String secret = "";
        switch (type) {
            case Constants.TOKEN_USER -> secret = JWTSecret_User;
            case Constants.TOKEN_MEMBER -> secret = JWTSecret_Member;
            case Constants.TOKEN_INS -> secret = JWTSecret_Ins;
            case Constants.TOKEN_ADMIN -> secret = JWTSecret_Admin;
            case Constants.TOKEN_FEIGN -> secret = JWTSecret_Feign;
        }

        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    /**
     * validate token
     *
     * @param authToken
     * @return
     */
    public boolean validate(int type, String authToken) {
        String secret = "";
        switch (type) {
            case Constants.TOKEN_USER -> secret = JWTSecret_User;
            case Constants.TOKEN_MEMBER -> secret = JWTSecret_Member;
            case Constants.TOKEN_INS -> secret = JWTSecret_Ins;
            case Constants.TOKEN_ADMIN -> secret = JWTSecret_Admin;
            case Constants.TOKEN_FEIGN -> secret = JWTSecret_Feign;
        }
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
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

    public boolean isExpired(Claims claims) {
        // 判断jwt是否过期
        return claims.getExpiration().before(new Date());
    }

    /**
     * 获取过期时间
     */
    public Long getDefaultExpirationInMs() {
        return this.JWTExpirationInMs;
    }

    /**
     * 获取请求头 key
     *
     * @return
     */
    public String getDefaultHeader() {
        return this.TOKEN_HEADER;
    }

}

