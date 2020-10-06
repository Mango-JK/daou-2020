package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Messages;
import com.daou.ssjd.dto.ChatsSendRequestDto;
import com.daou.ssjd.service.ChatsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public Messages sendMessages(@DestinationVariable("postId") int postId, ChatsSendRequestDto requestDto){
        log.info(requestDto.getUserId()+"/" + requestDto.getContent());
        return chatsService.sendMessage(postId, requestDto);
    }

    /**
     * 2. 게시글별 메세지 찾기
     */
    @GetMapping("/api/chats/{postId}")
    public ResponseEntity findByPostId(@PathVariable("postId") int postId) throws Exception {
        List<Messages> messagesList = new ArrayList<>();
        try{
            messagesList = chatsService.findByPostId(postId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(messagesList, HttpStatus.OK);
    }

}