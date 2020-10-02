package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>, PostsRepositoryCustom {

    Optional<Posts> findByPostId(Long postId);

    @Override
    Page<Posts> findAll(Pageable pageable);

    Page<Posts> findAllByLanguage(String language, Pageable pageable);

    Page<Posts> findAllByUserUserId(Long userId, Pageable pageable);

    Page<Posts> findAllByProblem_ProblemType(String sourceType, Pageable pageable);

    Page<Posts> findAllByLanguageAndProblem_ProblemType(String language, String sourceType, Pageable pageable);

    Page<Posts> searchAllByKeyword(String keyword, Pageable pageable);

}
