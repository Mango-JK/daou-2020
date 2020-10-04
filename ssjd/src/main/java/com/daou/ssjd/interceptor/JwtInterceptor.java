package com.daou.ssjd.interceptor;

import com.daou.ssjd.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
        throws Exception {
        System.out.println(request.getMethod() + " :" + request.getServletPath());

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            // req의 헤더 중 auth_token을 찾음
            String token = request.getHeader("Authorization");
            if (token != null && token.length() > 0) {
                // 유효하면 진행, 아니면 예외 던짐
                jwtService.checkValid(token);
                log.trace("유효한 토큰: {}", token);
                return true;
            } else {
                throw new RuntimeException("인증 토큰이 없습니다.");
            }
        }
    }
}
