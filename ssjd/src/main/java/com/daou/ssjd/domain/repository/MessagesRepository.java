package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
    Optional<List<Messages>> findAllByPostsPostId(Long postId);
}
