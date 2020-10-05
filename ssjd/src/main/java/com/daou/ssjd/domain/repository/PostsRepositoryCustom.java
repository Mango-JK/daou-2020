package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsRepositoryCustom {
    Page<Posts> searchAllByKeyword(String keyword, Pageable pageable);

    Page<Posts> searchAllByProblemSite(String problemSite, String keyword, Pageable pageable);

    Page<Posts> searchAllByLanguage(String language, String keyword, Pageable pageable);

    Page<Posts> searchAllByPlatform(String language, String problemSite, String keyword, Pageable pageable);
}
