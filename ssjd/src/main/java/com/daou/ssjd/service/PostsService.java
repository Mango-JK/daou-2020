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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Page<Posts> findAllPosts(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    /**
     * 5. 언어별 풀이 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPostsByLanguage(String language, Pageable pageable) {
        return postsRepository.findAllByLanguage(language, pageable);
    }

    /**
     * 6. 플랫폼별 풀이 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPostsByPlatform(String sourceType, Pageable pageable) {
        return postsRepository.findAllByProblem_ProblemType(sourceType, pageable);
    }

    /**
     * 7. 언어 + 플랫폼별 풀이 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPostsByLanguageAndPlatform(String language, String sourceType, Pageable pageable) {
        return postsRepository.findAllByLanguageAndProblem_ProblemType(language, sourceType, pageable);
    }

    /**
     * 8. 유저별 풀이 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPostsByUser(Long userId, Pageable pageable) {
        return postsRepository.findAllByUserUserId(userId, pageable);
    }

//    /**
//     * 9. 게시글 검색 (타이틀) + 페이징
//     */
//    @Transactional(readOnly = true)
//    public Page<Posts> searchAllPostsByKeyword(String keyword, Pageable pageable) {
//        Specification<Posts> spec = where(PostsSpecs.titleLike(keyword));
//        Page<Posts> result = postsRepository.searchAllPostsByKeyword(spec, pageable);
//        return result;
//    }
}
