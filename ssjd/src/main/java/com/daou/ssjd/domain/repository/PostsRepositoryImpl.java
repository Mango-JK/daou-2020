package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Posts;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static com.daou.ssjd.domain.entity.QPosts.posts;

@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Posts> searchAllByKeyword(String keyword, Pageable pageable) {
        QueryResults<Posts> results = queryFactory
                .selectFrom(posts)
                .where(posts.title.contains(keyword).or(posts.content.contains(keyword)).or(posts.problem.problemTitle.contains(keyword)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Posts> searchAllByProblemSite(String problemSite, String keyword, Pageable pageable) {
        QueryResults<Posts> results = queryFactory
                .selectFrom(posts)
                .where(posts.problem.problemSite.eq(problemSite).and(posts.title.contains(keyword).or(posts.content.contains(keyword).or(posts.problem.problemTitle.contains(keyword)))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Posts> searchAllByLanguage(String language, String keyword, Pageable pageable) {
        QueryResults<Posts> results = queryFactory
                .selectFrom(posts)
                .where(posts.language.eq(language).and(posts.title.contains(keyword).or(posts.content.contains(keyword).or(posts.problem.problemTitle.contains(keyword)))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Posts> searchAllByPlatform(String language, String problemSite, String keyword, Pageable pageable) {
        QueryResults<Posts> results = queryFactory
                .selectFrom(posts)
                .where(posts.language.eq(language).and(posts.problem.problemSite.eq(problemSite).or(posts.title.contains(keyword).or(posts.content.contains(keyword).or(posts.problem.problemTitle.contains(keyword))))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
