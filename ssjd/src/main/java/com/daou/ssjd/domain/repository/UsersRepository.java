package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
