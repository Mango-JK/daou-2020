package com.daou.ssjd.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "messages")
public class Messages{

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @JsonBackReference
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    public void createdDate(){this.createdDate = LocalDateTime.now();}

    @Builder
    public Messages(Users user, Posts posts, String content) {
        this.users = user;
        this.posts = posts;
        this.content = content;
    }
}
