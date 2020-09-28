package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Problems;
import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.domain.repository.UsersRepository;
import com.daou.ssjd.dto.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;

//    public UsersService(UsersRepository usersRepository) { this.usersRepository = usersRepository; }

    /**
    * 1. 유저 회원 가입
     */
    public Users saveUser(UsersSaveRequestDto usersSaveRequestDto) {
        validateDuplicateUser(usersSaveRequestDto);
        return usersRepository.save(Users.builder()
                .nickname(usersSaveRequestDto.getNickname())
                .password(usersSaveRequestDto.getPassword())
                .build()
        );
    }

    private void validateDuplicateUser(UsersSaveRequestDto usersSaveRequestDto) {
        usersRepository.findByNickname(usersSaveRequestDto.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Users> findByNickname(String nickname) {
        return usersRepository.findByNickname(nickname);
    }

    public Users findById(long userId){
        Users user = usersRepository.findByUserId(userId);
        return usersRepository.save(user);
    }

}
