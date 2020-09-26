package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.domain.entity.Users;
import com.daou.ssjd.dto.PostsSaveRequestDto;
import com.daou.ssjd.dto.PostsUpdateRequestDto;
import com.daou.ssjd.service.PostsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "Posts")
@RequiredArgsConstructor
@RequestMapping("/api/")
@RestController
public class PostsController {

    private final PostsService postsService;

    /**
     * 1. 게시글 등록
     */
    @PostMapping("/posts")
    public Posts savePosts(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.savePost(requestDto);
    }

    /**
     * 2. 게시글 수정
     */
    @PutMapping("/posts/{postId}")
    public void updatePosts(@PathVariable("postId") long postId, @RequestBody PostsUpdateRequestDto requestDto) {
        postsService.updatePost(postId, requestDto);
    }

    /**
     * 3. 게시글 삭제
     */
    @DeleteMapping("/posts/{postId}")
    public void deletePosts(@PathVariable("postId") long postId) {
        postsService.deletePost(postId);
    }

    /**
     * 4. 전체 게시글 조회
     */
    @GetMapping("/posts")
    public ResponseEntity findAllPosts(@RequestParam("pageNum") int pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = postsService.findAllPosts(pageRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 5. 언어별 게시글 조회
     */
    @GetMapping("/posts/{language}")
    public ResponseEntity findAllByLanguage(@PathVariable("language") String language, int pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum,6, Sort.by("modifiedDate").descending());
        Page<Posts> result = postsService.findAllPostsByLanguage(language, pageRequest);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 6. 플랫폼별 게시글 조회
     */
    @GetMapping("/problems/{sourceType}/posts")
    public ResponseEntity findAllByPlatform(@PathVariable("sourceType") String sourceType, int pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum,6, Sort.by("modifiedDate").descending());
        Page<Posts> result = postsService.findAllPostsByPlatform(sourceType, pageRequest);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 7. 유저별 게시글 조회
     */
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity findAllByUser(@PathVariable("userId") Long userId, int pageNum){
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = postsService.findAllPostsByUser(userId, pageRequest);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
