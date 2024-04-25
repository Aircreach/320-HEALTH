package com.air.health.common.model;

import org.springframework.beans.factory.annotation.Value;

public class TokenModel {

    private String accessToken;

    @Value("${app.jwt.type}")
    private String tokenType = "Bearer";

    public TokenModel(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
