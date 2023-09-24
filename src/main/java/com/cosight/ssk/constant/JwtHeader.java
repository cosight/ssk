package com.cosight.ssk.constant;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JwtHeader {

    ALGORITHM(SignatureAlgorithm.ES256.getValue()),
    TOKEN_TYPE("JWT");

    private final String value;

}
