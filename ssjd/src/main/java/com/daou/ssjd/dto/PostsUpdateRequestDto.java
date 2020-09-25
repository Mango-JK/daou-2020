package com.daou.ssjd.dto;

import com.daou.ssjd.domain.entity.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PostsUpdateRequestDto {
    private Long userId;
    private String problemLink;
    private String problemType;
    private String problemTitle;
    private List<Messages> messages;
    private String language;
    private String title;
    @Lob
    private String content;
    @Lob
    private String code;
}
