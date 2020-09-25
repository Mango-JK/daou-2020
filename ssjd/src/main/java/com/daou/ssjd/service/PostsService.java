package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.domain.entity.Problems;
import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.domain.repository.PostsRepository;
import com.daou.ssjd.dto.PostsSaveRequestDto;
import com.daou.ssjd.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UsersService usersService;
    private final ProblemsService problemsService;

    public Posts findByPostId(long postId) {
        return postsRepository.findByPostId(postId);
    }

    /**
     * 1. 게시글 생성
     */
    @Transactional
    public Posts savePost(PostsSaveRequestDto responseDto) {
        Users user = usersService.findById(responseDto.getUserId());
        Problems problem = new Problems(responseDto.getProblemLink(), responseDto.getProblemType(), responseDto.getProblemTitle());
        problemsService.saveProblems(problem);

        return postsRepository.save(Posts.builder()
                .user(user)
                .problem(problem)
                .language(responseDto.getLanguage())
                .title(responseDto.getTitle())
                .content(responseDto.getContent())
                .code(responseDto.getCode())
                .build()
        );
    }

    /**
     * 2. 게시글 수정
     */
    @Transactional
    public void updatePost(long postId, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findByPostId(postId);
        long deletedProblem = post.getProblem().getProblemId();
        Problems problem = new Problems(requestDto.getProblemLink(), requestDto.getProblemType(), requestDto.getProblemTitle());
        post.update(post.getUser(), problem, post.getMessages(), requestDto.getLanguage(), requestDto.getTitle(),
                requestDto.getContent(), requestDto.getCode());
        problemsService.deleteProblem(deletedProblem);
    }

    /**
     * 3. 게시글 삭제
     */
    @Transactional
    public void deletePost(long postId) {
        Posts deleteTargetPost = postsRepository.findByPostId(postId);
        postsRepository.delete(deleteTargetPost);
    }

    /**
     * 4. 전체 게시글 조회
     */
    public Page<Posts> findAllPosts(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }


}
