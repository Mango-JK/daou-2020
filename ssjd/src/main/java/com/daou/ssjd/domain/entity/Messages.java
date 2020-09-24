package com.daou.ssjd.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "messages")
public class Messages extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Posts post;

    @Lob
    @Column(name = "content")
    private String content;

}
