package com.company.insuranceapp.model.response;

import lombok.Data;

@Data
public class JWTResponse {
    private final String accessToken;
    private final String refreshToken;
}
