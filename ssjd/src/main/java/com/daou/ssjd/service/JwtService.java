package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Users;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtService {
    
    private String salt = "ssjd";
    
    private Long expireMin = 60L;

    /**
     * 로그인 성공시 사용자 정보 기반 jwt 토큰 반환
     * @param user
     * @return
     */
    public String create(final Users user) {
        log.trace("time: {}", expireMin);
        final JwtBuilder builder = Jwts.builder();

        // 헤더 설정, Token = Header + Payload + Signature
        builder.setHeaderParam("typ", "JWT");

        // claim 포함하여 payload 설정
        builder.setSubject("로그인 토큰")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
                .claim("User", user);

        // secret key를 사용하여 암호화
        builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());

        // 직렬화
        final String jwt = builder.compact();
        log.debug("토큰 발행: {}", jwt);
        return jwt;
    }

    /**
     * 전달받은 토큰 검증 문제가 있으면 예외 발생
     * @param jwt
     */
    public void checkValid(final String jwt) {
        log.trace("토큰 검증: {}", jwt);
        Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
    }

    /**
     * jwt 토큰 분석하여 필요한 정보 반환
     * @param jwt
     * @return
     */
    public Map<String, Object> get(final String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        log.trace("claims: {}", claims);
        return claims.getBody();
    }
}
