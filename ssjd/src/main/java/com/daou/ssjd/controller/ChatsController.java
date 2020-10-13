package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Messages;
import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.dto.ChatsSendRequestDto;
import com.daou.ssjd.service.ChatsService;
import com.daou.ssjd.service.UsersService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@Api(tags = "chats")
@RequiredArgsConstructor
@RestController
public class ChatsController {

    private final ChatsService chatsService;
    private final UsersService usersService;

    /**
     * 1. 메시지 보내기
     */
    //RequestMapping과는 상관없다 (/app/chats/{postId}로 메시지 받음)
    @MessageMapping("/chats/{postId}")
    @SendTo("/topic/receive/{postId}")
    public Messages sendMessages(@DestinationVariable("postId") int postId, @Payload ChatsSendRequestDto requestDto){
        if(requestDto.getMessageType() == ChatsSendRequestDto.MessageType.JOIN){
            Users user = usersService.findById(requestDto.getUserId());
            //user의 join만을 알리기 때문에 post정보는 필요없다
            return new Messages(user, new Posts(), "");
        }
        return chatsService.sendMessage(postId, requestDto);
    }

    /**
     * 2. 게시글별 메세지 찾기
     */
    @GetMapping("/api/chats/{postId}")
    public ResponseEntity findByPostId(@PathVariable("postId") int postId) throws Exception {
        List<Messages> messagesList;
        try{
            messagesList = chatsService.findByPostId(postId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(messagesList, HttpStatus.OK);
    }

}