package com.daou.ssjd.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatsSendRequestDto {

    public enum MessageType{ JOIN, SEND }

    @NotNull
    private int userId;
    private String content;
    private MessageType messageType;


    @Builder
    public ChatsSendRequestDto(int userId, String content, MessageType messageType) {
        this.userId = userId;
        this.content = content;
        this.messageType = messageType;
    }
}
