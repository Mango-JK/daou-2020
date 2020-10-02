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
        System.out.printf("IMPL로 왔다");
        
        QueryResults<Posts> result = queryFactory
                .select(posts)
                .where(posts.content.eq(keyword))
                .offset(6)
                .limit(0)
                .fetchResults();
        System.out.printf("result : " + result.toString());
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
