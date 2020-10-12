package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.domain.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer>, PostsRepositoryCustom {

    @Override
    Page<Posts> findAll(Pageable pageable);

    Page<Posts> findAllByLanguage(String language, Pageable pageable);

    Page<Posts> findAllByUserUserId(int userId, Pageable pageable);

    Page<Posts> findAllByProblem_ProblemSite(String problemSite, Pageable pageable);

    Page<Posts> findAllByLanguageAndProblem_ProblemSite(String language, String problemSite, Pageable pageable);

    Long countPostsByUserUserIdEquals(int userId);
}
