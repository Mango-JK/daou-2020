package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.domain.repository.UsersRepository;
import com.daou.ssjd.dto.UsersSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    void 회원가입() {
        // given
        UsersSaveRequestDto usersSaveRequestDto = UsersSaveRequestDto.builder()
                .nickname("ssjd")
                .password("example")
                .build();

        // when
        Users savedUser = usersService.saveUser(usersSaveRequestDto);

        // then
        Users findUser = usersService.findByNickname(savedUser.getNickname()).get();
        assertThat(usersSaveRequestDto.getNickname()).isEqualTo(findUser.getNickname());
    }

    @Test
    void 중복_회원_예외() {
        // given
        UsersSaveRequestDto user1 = new UsersSaveRequestDto("dupTest", "");
        UsersSaveRequestDto user2 = new UsersSaveRequestDto("dupTest", "");

        // when
        usersService.saveUser(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> usersService.saveUser(user2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
