package com.daou.ssjd.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
public class ChatsSendRequestDto {
    @NotNull
    private Long userId;
    @Lob
    private String content;

    @Builder
    public ChatsSendRequestDto(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
