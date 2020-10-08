package com.daou.ssjd.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPosts is a Querydsl query type for Posts
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPosts extends EntityPathBase<Posts> {

    private static final long serialVersionUID = 1797470012L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPosts posts = new QPosts("posts");

    public final com.daou.ssjd.domain.entity.QBaseTimeEntity _super = new com.daou.ssjd.domain.entity.QBaseTimeEntity(this);

    public final StringPath code = createString("code");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath language = createString("language");

    public final ListPath<Messages, com.daou.ssjd.domain.entity.QMessages> messages = this.<Messages, com.daou.ssjd.domain.entity.QMessages>createList("messages", Messages.class, com.daou.ssjd.domain.entity.QMessages.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> postId = createNumber("postId", Integer.class);

    public final com.daou.ssjd.domain.entity.QProblems problem;

    public final StringPath title = createString("title");

    public final com.daou.ssjd.domain.entity.QUsers user;

    public QPosts(String variable) {
        this(Posts.class, forVariable(variable), INITS);
    }

    public QPosts(Path<? extends Posts> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPosts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPosts(PathMetadata metadata, PathInits inits) {
        this(Posts.class, metadata, inits);
    }

    public QPosts(Class<? extends Posts> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.problem = inits.isInitialized("problem") ? new com.daou.ssjd.domain.entity.QProblems(forProperty("problem")) : null;
        this.user = inits.isInitialized("user") ? new com.daou.ssjd.domain.entity.QUsers(forProperty("user")) : null;
    }

}

