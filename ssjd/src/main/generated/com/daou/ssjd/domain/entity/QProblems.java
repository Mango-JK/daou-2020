package com.daou.ssjd.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProblems is a Querydsl query type for Problems
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProblems extends EntityPathBase<Problems> {

    private static final long serialVersionUID = 1308144043L;

    public static final QProblems problems = new QProblems("problems");

    public final NumberPath<Long> problemId = createNumber("problemId", Long.class);

    public final StringPath problemLink = createString("problemLink");

    public final StringPath problemSite = createString("problemSite");

    public final StringPath problemTitle = createString("problemTitle");

    public QProblems(String variable) {
        super(Problems.class, forVariable(variable));
    }

    public QProblems(Path<? extends Problems> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProblems(PathMetadata metadata) {
        super(Problems.class, metadata);
    }

}

