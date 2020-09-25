package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public Users findById(long userId){
        Users user = usersRepository.findByUserId(userId);
        return usersRepository.save(user);
    }

}
