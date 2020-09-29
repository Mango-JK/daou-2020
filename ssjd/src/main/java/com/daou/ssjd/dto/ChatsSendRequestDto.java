package com.daou.ssjd.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
public class ChatsSendRequestDto {
    private Long userId;
    @Lob
    private String content;
}
