package com.cosight.ssk.filter;

import static java.util.Objects.isNull;

import com.cosight.ssk.jwt.JwtProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 요청에서 토큰을 꺼내 인증을 진행합니다.
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("doFilterInternal");

        String token = jwtProvider.resolveJwt(request);

        // 토큰이 없거나 만료된 경우 인증 없이 필터 통과
        if (isNull(token) || !jwtProvider.isValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = jwtProvider.getAuthentication(token);

        // 인증이 끝난 뒤 SecurityContext 에 Authentication 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }



    // private boolean isLoggedOut(String accessToken) {
    //     return authRepository.existsById(accessToken);
    // }

}
