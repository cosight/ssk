package com.cosight.ssk.jwt;

import com.cosight.ssk.constant.JwtTime;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * JWT 관련 모듈을 제공하는 클래스입니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${ssk.jwt.secret}")
    private String secretKey;

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // private final AuthRepository authRepository;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * access token 을 생성합니다.
     *
     * @param username 사용자 아이디
     * @param roles    사용자가 가지고 있는 권한
     * @return 사용자 아이디, 권한, 발행일자, 유효기간 정보가 담긴 access token
     */
    public String createAccessToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        return this.createJwt(claims, JwtTime.ACCESS_TOKEN_EXPIRATION_TIME.getTime());
    }

    /**
     * refresh token 을 생성하고, token 전체가 아니라 UUID 만 데이터베이스에 저장합니다.
     *
     * @param uuid UUID
     * @return UUID, 발행일자, 유효기간 정보가 담긴 refresh token
     */
    public String createRefreshToken(String uuid) {
        Claims claims = Jwts.claims();
        claims.put("uuid", uuid);

        return this.createJwt(claims, JwtTime.REFRESH_TOKEN_EXPIRATION_TIME.getTime());
    }

    private String createJwt(Claims claims, Long validTime) {
        Date issuedAt = new Date();
        Date expiredAt = new Date(issuedAt.getTime() + validTime);

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(issuedAt)
                   .setExpiration(expiredAt)
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();
    }

    /**
     * JWT 를 반환합니다.
     *
     * @param request 클라이언트의 요청
     * @return AUTHORIZATION 헤더에 담긴 JWT
     */
    public String resolveJwt(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    public boolean isValid(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

        return !claimsJws.getBody().getExpiration().before(new Date());
    }

    public Authentication getAuthentication(String token) {
        // 토큰으로 유저 아이디 대신 UUID 를 통해 UserDetails 를 가져옴
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUuid(token));

        // 비밀번호 확인
        // if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        //     throw new BadCredentialsException(userDetails.getUsername(), "Invalid password");
        // }

        // Authentication Principal 에 SessionId, Credential 에 JWT 저장
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUuid(String token) {
        return Jwts.parser()
                   .setSigningKey(secretKey)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    // private String getAccessToken(HttpServletRequest request) {
    //     String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
    //
    //     if (!StringUtils.hasText(auth) || !auth.startsWith("Bearer ")) {
    //         return null;
    //     }
    //
    //     return auth.substring(7);
    // }

/*
    // "Bearer: XXX" 모양의 헤더에서 "Bearer: " 를 제외하고 JWT 를 가져옴
    // String accessToken = this.getAccessToken(request);

    // access token 이 null 이 아니면 해당 토큰이 유효한 지 검증
        if (isNull(accessToken)) {
        filterChain.doFilter(request, response);
        return;
    }

        if (this.isLoggedOut(accessToken)) {
        throw new IllegalArgumentException("이미 로그아웃한 회원입니다.");
    }

        JwtUtils.getUsername(accessToken);

*/

}
