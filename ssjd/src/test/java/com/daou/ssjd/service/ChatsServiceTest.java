package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Messages;
import com.daou.ssjd.dto.ChatsSendRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatsServiceTest {
    @Autowired
    private ChatsService chatsService;

    @Autowired
    PostsService postsService;

    @Test
    @Transactional
    public void 메시지_전송() {
        // given
        final int postId = 5;

        ChatsSendRequestDto dto = ChatsSendRequestDto.builder()
                .userId(1)
                .content("This is Test message")
                .messageType(ChatsSendRequestDto.MessageType.SEND)
                .build();

        Messages msg = chatsService.sendMessage(postId, dto);

        // when
        List<Messages> msgs = chatsService.findByPostId(postId);

        // then
        assertThat(msgs).contains(msg);
    }
}
