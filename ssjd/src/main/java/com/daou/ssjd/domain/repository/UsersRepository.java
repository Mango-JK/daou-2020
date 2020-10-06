package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserId(int userId);
    Optional<Users> findByNickname(String nickname);
}
