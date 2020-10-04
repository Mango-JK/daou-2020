package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.dto.UsersRequestDto;
import com.daou.ssjd.dto.UsersUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.NoSuchElementException;

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
        UsersRequestDto usersRequestDto = UsersRequestDto.builder()
                .nickname("ssjd")
                .password("example")
                .build();

        // when
        Users savedUser = usersService.saveUser(usersRequestDto);

        // then
        Users findUser = usersService.findByNickname(savedUser.getNickname()).get();
        assertThat(usersRequestDto.getNickname()).isEqualTo(findUser.getNickname());
    }

    @Test
    void 중복_회원_예외() {
        // given
        UsersRequestDto user1 = new UsersRequestDto("dupTest", "");
        UsersRequestDto user2 = new UsersRequestDto("dupTest", "");

        // when
        usersService.saveUser(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> usersService.saveUser(user2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void 로그인() {
        // given
        UsersRequestDto givenUser = new UsersRequestDto("loginTest", "testPassword");
        UsersRequestDto loginUser = new UsersRequestDto("loginTest", "testPassword");

        // when
        usersService.saveUser(givenUser);
        Users findUser = usersService.userLogIn(loginUser);

        // then
        assertThat(givenUser.getNickname()).isEqualTo(findUser.getNickname());
        assertThat(givenUser.getPassword()).isEqualTo(findUser.getPassword());
    }

    @Test
    void 비밀번호_불일치_예외() {
        // given
        UsersRequestDto givenUser = new UsersRequestDto("givenUser", "givenPassword");
        UsersRequestDto testUser = new UsersRequestDto("givenUser", "notEqualPassword");

        // when
        usersService.saveUser(givenUser);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> usersService.userLogIn(testUser));

        // then
        assertThat(e.getMessage()).isEqualTo("비밀번호가 일치하지 않습니다.");
    }

    @Test
    void 회원_없음_예외() {
        // given
        UsersRequestDto testUser = new UsersRequestDto("testUser", "testPassword");

        // when
        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> usersService.userLogIn(testUser));

        // then
        assertThat(e.getMessage()).isEqualTo("No value present");
    }

    @Test
    void 비밀번호_변경() {
        // given
        UsersRequestDto testUser = UsersRequestDto.builder()
                .nickname("testUser")
                .password("testPassword")
                .build();
        UsersRequestDto changePasswordUser = UsersRequestDto.builder()
                .nickname("testUser")
                .password("changeTestPassword")
                .build();

        // when
        usersService.saveUser(testUser);
        Users changedUser = usersService.changePassword(changePasswordUser);

        // then
        assertThat(testUser.getNickname()).isEqualTo(changedUser.getNickname());
        assertThat(testUser.getPassword()).isNotEqualTo(changedUser.getPassword());
    }

    @Test
    void 닉네임_변경() {
        // given
        UsersRequestDto testUser = UsersRequestDto.builder()
                .nickname("testUser")
                .password("testPassword")
                .build();
        UsersUpdateRequestDto changeNicknameUser = UsersUpdateRequestDto.builder()
                .nickname("testUser")
                .newNickname("changedTestNickname")
                .build();

        // when
        usersService.saveUser(testUser);
        Users changedUser = usersService.changeNickname(changeNicknameUser);

        // then
        assertThat(testUser.getNickname()).isNotEqualTo(changedUser.getNickname());
    }

    @Test
    void 닉네임_변경_예외() {
        // given
        UsersRequestDto testUser = UsersRequestDto.builder()
                .nickname("testUser")
                .password("testUserPassword")
                .build();
        UsersRequestDto existUser = UsersRequestDto.builder()
                .nickname("existUser")
                .password("existUserPassword")
                .build();
        UsersUpdateRequestDto changeNicknameUser = UsersUpdateRequestDto.builder()
                .nickname("testUser")
                .newNickname("existUser")
                .build();

        // when
        usersService.saveUser(testUser);
        usersService.saveUser(existUser);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> usersService.changeNickname(changeNicknameUser));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
