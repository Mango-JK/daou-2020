package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.domain.entity.Problems;
import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.domain.repository.PostsRepository;
import com.daou.ssjd.dto.PostsSaveRequestDto;
import com.daou.ssjd.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UsersService usersService;
    private final ProblemsService problemsService;

    /**
     * 1. 게시글 생성
     */
    @Transactional
    public Posts savePost(PostsSaveRequestDto responseDto) {
        Users user = usersService.findById(responseDto.getUserId());
        Problems problem = new Problems(responseDto.getProblemLink(), responseDto.getProblemSite(), responseDto.getProblemTitle());
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
    public void updatePost(int postId, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(postId).get();
        int deletedProblem = post.getProblem().getProblemId();
        Problems problem = new Problems(requestDto.getProblemLink(), requestDto.getProblemSite(), requestDto.getProblemTitle());
        post.update(post.getUser(), problem, requestDto.getLanguage(), requestDto.getTitle(),
                requestDto.getContent(), requestDto.getCode());
        problemsService.deleteProblem(deletedProblem);
    }

    /**
     * 3. 게시글 삭제
     */
    @Transactional
    public void deletePost(int postId) {
        Posts deleteTargetPost = postsRepository.findById(postId).get();
        postsRepository.delete(deleteTargetPost);
    }

    /**
     * 4. 게시글 상세 조회
     */
    public Optional<Posts> findByPostId(int postId) {
        return postsRepository.findById(postId);
    }

    /**
     * 5. 전체 게시글 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPosts(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    /**
     * 6. 언어별 풀이 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPostsByLanguage(String language, Pageable pageable) {
        return postsRepository.findAllByLanguage(language, pageable);
    }

    /**
     * 7. 플랫폼별 풀이 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPostsByProblemSite(String problemSite, Pageable pageable) {
        return postsRepository.findAllByProblem_ProblemSite(problemSite, pageable);
    }

    /**
     * 8. 언어 + 플랫폼별 풀이 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPostsByLanguageAndProblemSite(String language, String problemSite, Pageable pageable) {
        return postsRepository.findAllByLanguageAndProblem_ProblemSite(language, problemSite, pageable);
    }

    /**
     * 9. 유저별 풀이 조회
     */
    @Transactional(readOnly = true)
    public Page<Posts> findAllPostsByUser(int userId, Pageable pageable) {
        return postsRepository.findAllByUserUserId(userId, pageable);
    }

    /**
     * 10. 통합 검색
     */
    public Page<Posts> searchAllByKeyword(String keyword, PageRequest pageRequest) {
        return postsRepository.searchAllByKeyword(keyword, pageRequest);
    }

    /**
     * 11. 플랫폼별 검색
     */
    public Page<Posts> searchAllByPlatform(String language, String problemSite, String keyword, PageRequest pageRequest) {
        if (language == null && problemSite == null) {
            return postsRepository.searchAllByKeyword(keyword, pageRequest);
        } else if (language == null) {
            return postsRepository.searchAllByProblemSite(problemSite, keyword, pageRequest);
        } else if (problemSite == null) {
            return postsRepository.searchAllByLanguage(language, keyword, pageRequest);
        }
        return postsRepository.searchAllByPlatform(language, problemSite, keyword, pageRequest);
    }

    /**
     * 12. 유저의 게시글 수 확인
     */
    @Transactional(readOnly = true)
    public Long countById(int userId) {
        return postsRepository.countPostsByUserUserIdEquals(userId);
    }
}
