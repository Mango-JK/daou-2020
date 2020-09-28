package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.domain.repository.UsersRepository;
import com.daou.ssjd.dto.UsersRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;

    /**
     * 1. 유저 회원 가입
     */
    public Users saveUser(UsersRequestDto usersRequestDto) {
        validateDuplicateUser(usersRequestDto);
        return usersRepository.save(Users.builder()
                .nickname(usersRequestDto.getNickname())
                .password(usersRequestDto.getPassword())
                .build()
        );
    }

    /**
     * 2. 중복 검사
     */
    private void validateDuplicateUser(UsersRequestDto usersRequestDto) {
        usersRepository.findByNickname(usersRequestDto.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 3. 로그인
     */
    public Users userLogIn(UsersRequestDto usersRequestDto) {
        Users findUser = usersRepository.findByNickname(usersRequestDto.getNickname()).get(); // 없으면 예외 발생
        if (usersRequestDto.getPassword() != findUser.getPassword()) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        } else {
            // 토큰 발행
            System.out.println("Hi");
            return findUser;
        }
    }
    
    public Optional<Users> findByNickname(String nickname) {
        return usersRepository.findByNickname(nickname);
    }

    public Users findById(long userId){
        Users user = usersRepository.findByUserId(userId);
        return usersRepository.save(user);
    }

}
