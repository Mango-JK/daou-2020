package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.dto.UsersRequestDto;
import com.daou.ssjd.service.JwtService;
import com.daou.ssjd.service.UsersService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "Users")
@RequiredArgsConstructor
@RequestMapping("/api/")
@RestController
@CrossOrigin(origins = "*")
public class UsersController {
    private final UsersService usersService;
    private final JwtService jwtService;

    /**
    * 1. 회원가입
     */
    @PostMapping("/users")
    public Users saveUsers(@RequestBody UsersRequestDto usersRequestDto) { return usersService.saveUser(usersRequestDto); }

    /**
     * 2. 로그인
     */
    @PostMapping("/users/login")
    public ResponseEntity<Map<String, Object>> userLogIn(@RequestBody UsersRequestDto usersRequestDto,
                                                      HttpServletResponse res) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            Users loginUser = usersService.userLogIn(usersRequestDto);

            // 성공시 토큰 생성
            String token = jwtService.create(loginUser);

            // 토큰을 req 의 헤더에 담아줌
            res.setHeader("jwt-auth-token", token);

            resultMap.put("status", true);
            resultMap.put("data", loginUser);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            log.error("로그인 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
