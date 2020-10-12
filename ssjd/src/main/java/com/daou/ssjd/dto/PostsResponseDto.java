package com.daou.ssjd.dto;

import com.daou.ssjd.domain.entity.Messages;
import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.domain.entity.Problems;
import com.daou.ssjd.domain.entity.Users;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostsResponseDto {
    private int postId;
    private Users user;
    private String language;
    private Problems problem;
    private List<Messages> messages = new ArrayList<>();
    private String title;
    private String content;
    private String code;

    public PostsResponseDto(Posts entity) {
        this.postId = entity.getPostId();
        this.user = entity.getUser();
        this.language = entity.getLanguage();
        this.problem = entity.getProblem();
        this.messages = entity.getMessages();
        this.title = entity.getTitle();
        this.content = entity.getTitle();
        this.code = entity.getCode();
    }
}
