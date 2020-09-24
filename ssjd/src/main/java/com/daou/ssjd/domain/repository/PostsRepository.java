package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
