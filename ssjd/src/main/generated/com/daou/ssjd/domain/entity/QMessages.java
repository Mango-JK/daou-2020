package com.daou.ssjd.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessages is a Querydsl query type for Messages
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessages extends EntityPathBase<Messages> {

    private static final long serialVersionUID = 1851924803L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessages messages = new QMessages("messages");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> messageId = createNumber("messageId", Long.class);

    public final QPosts posts;

    public final QUsers users;

    public QMessages(String variable) {
        this(Messages.class, forVariable(variable), INITS);
    }

    public QMessages(Path<? extends Messages> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessages(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessages(PathMetadata metadata, PathInits inits) {
        this(Messages.class, metadata, inits);
    }

    public QMessages(Class<? extends Messages> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.posts = inits.isInitialized("posts") ? new QPosts(forProperty("posts"), inits.get("posts")) : null;
        this.users = inits.isInitialized("users") ? new QUsers(forProperty("users")) : null;
    }

}

