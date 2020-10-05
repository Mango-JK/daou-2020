package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Messages;
import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.domain.repository.MessagesRepository;
import com.daou.ssjd.dto.ChatsSendRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ChatsService {
    private final MessagesRepository messagesRepository;
    private final PostsService postsService;
    private final UsersService usersService;

    @Transactional
    public Messages sendMessage(long postId, ChatsSendRequestDto responseDto) {
        Users user = usersService.findById(responseDto.getUserId());
        Optional<Posts> post = postsService.findByPostId(postId);

        return messagesRepository.save(Messages.builder()
                .user(user)
                .posts(post.get())
                .content(responseDto.getContent())
                .build()
        );
    }

    public List<Messages> findByPostId(Long postId) {
        return messagesRepository.findAllByPostsPostId(postId).get();
    }
}
