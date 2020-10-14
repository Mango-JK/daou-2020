package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.domain.repository.UsersRepository;
import com.daou.ssjd.dto.UsersRequestDto;
import com.daou.ssjd.dto.UsersSaveRequestDto;
import com.daou.ssjd.dto.UsersUpdateRequestDto;
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
    public Users saveUser(UsersSaveRequestDto usersSaveRequestDto) {
        validateDuplicateUser(usersSaveRequestDto);
        return usersRepository.save(Users.builder()
                .nickname(usersSaveRequestDto.getNickname())
                .password(usersSaveRequestDto.getPassword())
                .salt(usersSaveRequestDto.getSalt())
                .build()
        );
    }

    /**
     * 2. 중복 검사
     */
    private void validateDuplicateUser(UsersSaveRequestDto usersSaveRequestDto) {
        usersRepository.findByNickname(usersSaveRequestDto.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 3. 로그인
     */
    public Users userLogIn(UsersRequestDto usersRequestDto) {
        Users findUser = usersRepository.findByNickname(usersRequestDto.getNickname()).get();
        if (!(usersRequestDto.getPassword().equals(findUser.getPassword()))) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        } else {
            return findUser;
        }
    }

    /**
     * 4. 닉네임으로 유저 찾기
     */
    public Optional<Users> findByNickname(String nickname) {
        return usersRepository.findByNickname(nickname);
    }

    /**
     * 5. id로 유저 찾기
     */
    public Users findById(int userId){
        Users user = usersRepository.findByUserId(userId);
        return user;
    }

    /**
     * 6. 비밀번호 변경
     */
    public Users changePassword(UsersRequestDto usersRequestDto) {
        Users user = usersRepository.findByNickname(usersRequestDto.getNickname()).get();
        user.updatePassword(usersRequestDto.getPassword());
        return user;
    }

    /**
     * 7. 닉네임 변경
     */
    public Users changeNickname(UsersUpdateRequestDto usersUpdateRequestDto) {
        Users user = usersRepository.findByNickname(usersUpdateRequestDto.getNickname()).get();
        UsersSaveRequestDto chkUser = UsersSaveRequestDto.builder()
                .nickname(usersUpdateRequestDto.getNewNickname())
                .password("dummyPassword")
                .salt("dummySalt")
                .build();
        validateDuplicateUser(chkUser);
        user.updateNickname(usersUpdateRequestDto.getNewNickname());
        return user;
    }

    /**
     * 8. 유저 삭제
     */
    public Users deleteUser(String nickname) {
        Users deleteTargetUser = usersRepository.findByNickname(nickname).get();
        usersRepository.delete(deleteTargetUser);
        return deleteTargetUser;
    }

}
