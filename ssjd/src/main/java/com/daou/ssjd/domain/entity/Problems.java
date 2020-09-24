package com.daou.ssjd.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor()
@Getter
@Entity
@Table(name = "problems")
public class Problems {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "problem_link")
    private String problemLink;

    @Column(name = "problem_type")
    private String problemType;

    @Column(name = "problem_title")
    private String problemTitle;

    @Builder
    public Problems(String problemLink, String problemType, String problemTitle) {
        this.problemLink = problemLink;
        this.problemTitle = problemTitle;
        this.problemType = problemType;
    }

}
