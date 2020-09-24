package com.daou.ssjd.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "language")
    private String language;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "problem_id")
    private Problems problem;

    @OneToMany(mappedBy = "post")
    private List<Messages> messages = new ArrayList<>();

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    @Lob
    private String content;

    @Column(name = "code")
    @Lob
    private String code;

    public void update(String language, Problems problem, String title, String content, String code, List<Messages> messages) {
        this.language = language;
        this.problem = problem;
        this.title = title;
        this.content = content;
        this.code = code;
        this.messages = messages;
        this.updateModifiedDate(LocalDateTime.now());
    }

}
