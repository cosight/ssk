package com.cosight.ssk.constant;

import java.util.Arrays;

public enum RoleType {

    ROLE_USER,
    ROLE_ADMIN;

    /**
     * 문자열로 구성된 결제 수단 구분을 통해 열거형을 추출합니다.
     *
     * @param type - 문자열로 구성된 결제 수단 구분
     * @return 문자열로 받은 결제 수단의 {@link RoleType}
     */
    public static RoleType of(String type) {
        return Arrays.stream(RoleType.values())
                     .filter(v -> v.name().equals(type))
                     .findAny()
                     .orElseThrow(IllegalArgumentException::new);
    }

}
