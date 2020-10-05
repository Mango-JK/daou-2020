package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Messages;
import com.daou.ssjd.dto.ChatsSendRequestDto;
import com.daou.ssjd.service.ChatsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Slf4j
@Api(tags = "chats")
@RequiredArgsConstructor
//@RequestMapping("/api/")
@RestController
public class ChatsController {

    private final ChatsService chatsService;

    /**
     * 1. 메시지 보내기
     */
    @MessageMapping("/send/{postId}")
    @SendTo("/sub/receive/{postId}")
    public Messages sendMessages(@DestinationVariable("postId") long postId, ChatsSendRequestDto requestDto){
        log.info(requestDto.getUserId()+"/" + requestDto.getContent());
        return chatsService.sendMessage(postId, requestDto);
    }

}