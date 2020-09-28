package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.dto.UsersSaveRequestDto;
import com.daou.ssjd.service.UsersService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "Users")
@RequiredArgsConstructor
@RequestMapping("/api/")
@RestController
@CrossOrigin(origins = "*")
public class UsersController {
    private final UsersService usersService;

    /**
    * 1. 회원가입
     */
    @PostMapping("/users")
    public Users saveUsers(@RequestBody UsersSaveRequestDto usersSaveRequestDto) { return usersService.saveUser(usersSaveRequestDto); }
}
