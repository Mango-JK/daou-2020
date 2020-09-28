package com.daou.ssjd.dto;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    @NotNull
    private Long userId;
    private String problemLink;
    private String problemType;
    private String problemTitle;
    private String language;
    private String title;
    @Lob
    private String content;
    @Lob
    private String code;

    @Builder
    public PostsSaveRequestDto(Long userId, String problemLink, String problemType, String problemTitle,
                               String language, String title, String content, String code) {
        this.userId = userId;
        this.problemLink = problemLink;
        this.problemType = problemType;
        this.problemTitle = problemTitle;
        this.language = language;
        this.title = title;
        this.content = content;
        this.code = code;
    }


}