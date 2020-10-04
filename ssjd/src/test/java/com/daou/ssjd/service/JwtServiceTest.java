package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    public void 토큰_해독_예외() {
        // given
        String testStr = "thisIsNotJwtToken";

        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> jwtService.get(testStr));

        // then
        assertThat(e.getMessage()).isEqualTo("토큰으로부터 정보를 얻을 수 없습니다.");
    }

    @Test
    public void 토큰_검증_예외() {
        // given
        String testStr = "thisIsNotJwtToken";

        // when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> jwtService.checkValid(testStr));

        // then
        assertThat(e.getMessage()).isEqualTo("토큰 검증을 통과하지 못했습니다.");
    }

    @Test
    public void 토큰_생성_확인() {
        // given
        Users user = Users.builder()
                .nickname("tokenTest")
                .password("password")
                .build();

        // when
        String jwtResult = jwtService.create(user);
        Map<String, Object> jwtInfo = jwtService.get(jwtResult);

        // then
        assertThat(jwtInfo.get("sub")).isEqualTo("로그인 토큰");
        assertThat(user.getNickname())
                .isEqualTo(jwtInfo.get("User").toString().split("=")[2].split("}")[0]);
    }
}
