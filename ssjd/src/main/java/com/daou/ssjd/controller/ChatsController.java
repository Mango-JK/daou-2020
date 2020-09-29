package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Messages;
import com.daou.ssjd.dto.ChatsSendRequestDto;
import com.daou.ssjd.service.ChatsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@Api(tags = "chat")
@RequiredArgsConstructor
@RequestMapping("/api/")
@RestController
public class ChatsController {

    private final ChatsService chatsService;

    /**
     * 1. 메시지 보내기
     */
    @PostMapping("/chats/{postId}")
    public Messages sendMessages(@PathVariable("postId") long postId, @RequestBody ChatsSendRequestDto requestDto) {
        return chatsService.sendMessage(postId, requestDto);
    }

}
