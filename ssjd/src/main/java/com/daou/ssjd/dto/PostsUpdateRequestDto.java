package com.daou.ssjd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Setter
@Getter
@AllArgsConstructor
public class PostsUpdateRequestDto {
    private int userId;
    private String problemLink;
    private String problemSite;
    private String problemTitle;
    private String language;
    private String title;
    private String content;
    private String code;
}
