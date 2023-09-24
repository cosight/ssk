package com.cosight.ssk.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JwtTime {

    ACCESS_TOKEN_EXPIRATION_TIME("Access 토큰 만료 시간은 30분입니다.", 1_000L * 60L * 30L),
    REFRESH_TOKEN_EXPIRATION_TIME("Refresh 토큰 만료 시간은 7일입니다.", 1_000L * 60L * 60L * 24L * 7L);

    private final String description;
    private final Long time;

}
