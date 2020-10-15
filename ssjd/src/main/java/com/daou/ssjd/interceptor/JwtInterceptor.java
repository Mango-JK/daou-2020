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

    @Autowired(required = true)
    private JwtService jwtService;

    // CSRF referer 검증
    private final String aceeptDomain = "http://localhost:8080/";

    public String getAceeptDomain() { return aceeptDomain; }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handler)
//        throws Exception {
//        log.info(request.getMethod() + " :" + request.getServletPath());
//        log.info("CSRF Referer check: " + request.getHeader("Referer").equals(getAceeptDomain()));
//
//        if (!(request.getHeader("Referer").equals(getAceeptDomain())))
//            throw new Exception("요청 도메인이 다릅니다.");
//
//        if (request.getMethod().equals("OPTIONS")) {
//            return true;
//        } else {
//            // req의 헤더 중 auth_token을 찾음
//            String token = request.getHeader("Authorization");
//            log.info("CSRF Token check: " + request.getHeader("CSRF_TOKEN").equals(request.getHeader("CSRF_TOKEN_IN_COOKIE")));
//
//            if (token != null) {
//                String[] bearerChk = token.split(" ");
//                if (bearerChk[0].equals("Bearer")) token = bearerChk[1];
//            }
//
//            if (token != null && token.length() > 0) {
//                // 유효하면 진행, 아니면 예외 던짐
//                try {
//                    jwtService.checkValid(token);
//                } catch(Exception e) {
//                    System.out.println(e.getMessage());
//                    throw new RuntimeException("토큰 검증 실패");
//                }
//                log.trace("유효한 토큰: {}" + token);
//                return true;
//            } else {
//                log.error("토큰 에러");
//                log.error(token);
//                throw new RuntimeException("인증 토큰이 없습니다.");
//            }
//        }
//    }
}
