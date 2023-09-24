package com.cosight.ssk.util;

import com.cosight.ssk.constant.JwtHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * static class 로 못 만듦
 */
@Slf4j
@Component
public class JwtUtils {


    public String getUsername(String accessToken) {
        // 2 hours
        Long expiredAt = 1_000 * 60L * 60L * 2L;

        Date ext = new Date();
        ext.setTime(ext.getTime() + expiredAt);

        // String jwt = Jwts.builder()
        //                  .setHeader(this.getJwtHeaders())
        //                  .setClaims(payloads)
        //                  .setExpiration(ext)
        //                  .signWith(SignatureAlgorithm.HS256, secretKey)
        //                  .compact();

        // return jwt;
        return null;
    }

    private Map<String, Object> getJwtHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", JwtHeader.TOKEN_TYPE.getValue());
        headers.put("alg", JwtHeader.ALGORITHM.getValue());

        return headers;
    }

}
