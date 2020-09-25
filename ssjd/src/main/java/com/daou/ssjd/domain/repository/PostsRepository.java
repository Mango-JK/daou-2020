package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    Posts findByPostId(long postId);

    @Override
    Page<Posts> findAll(Pageable pageable);

    @Override
    List<Posts> findAll();
}
