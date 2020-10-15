package com.daou.ssjd.dto;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    @NotNull
    private int userId;
    private String problemLink;
    private String problemSite;
    private String problemTitle;
    private String language;
    private String title;
    private String content;
    private String code;

    @Builder
    public PostsSaveRequestDto(int userId, String problemLink, String problemSite, String problemTitle,
                               String language, String title, String content, String code) {
        this.userId = userId;
        this.problemLink = problemLink;
        this.problemSite = problemSite;
        this.problemTitle = problemTitle;
        this.language = language;
        this.title = title;
        this.content = content;
        this.code = code;
    }
}
