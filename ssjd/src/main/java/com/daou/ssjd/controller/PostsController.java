package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.dto.PostsResponseDto;
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
    public ResponseEntity savePosts(@RequestBody PostsSaveRequestDto requestDto) throws Exception {
        try {
            PostsResponseDto responseDto = new PostsResponseDto(postsService.savePost(requestDto));
        } catch (Exception e) {
            return new ResponseEntity(requestDto, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(requestDto, HttpStatus.OK);
    }

    /**
     * 2. 게시글 수정
     */
    @PutMapping("/posts/{postId}")
    public ResponseEntity updatePosts(@PathVariable("postId") long postId, @RequestBody PostsUpdateRequestDto requestDto) throws Exception {
        try {
            postsService.updatePost(postId, requestDto);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 3. 게시글 삭제
     */
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePosts(@PathVariable("postId") long postId) throws Exception {
        try {
            postsService.deletePost(postId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 4. 게시글 상세 조회
     */
    @GetMapping("/posts/{postId}")
    public ResponseEntity findOne(@PathVariable("postId") Long postId) {
        Posts dto = postsService.findByPostId(postId).get();
        if (dto == null) {
            return new ResponseEntity(dto, HttpStatus.NOT_EXTENDED);
        }
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * 5. 전체 게시글 조회
     */
    @GetMapping("/posts")
    public ResponseEntity findAllPosts(@RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPosts(pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 6. 언어별 게시글 조회
     */
    @GetMapping("/posts/platform/{language}")
    public ResponseEntity findAllByLanguage(@PathVariable("language") String language, @RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPostsByLanguage(language, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 7. 플랫폼별 게시글 조회
     */
    @GetMapping("/posts/problems/{sourceType}")
    public ResponseEntity findAllByPlatform(@PathVariable("sourceType") String sourceType, @RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPostsByPlatform(sourceType, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 8. 언어 + 플랫폼별 게시글 조회
     */
    @GetMapping("/posts/platform/language")
    public ResponseEntity findAllByPlatformAndLanguage(@RequestParam("language") String language, @RequestParam("sourceType") String sourceType, int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPostsByLanguageAndPlatform(language, sourceType, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 9. 유저별 게시글 조회
     */
    @GetMapping("/posts/users/{userId}")
    public ResponseEntity findAllByUser(@PathVariable("userId") Long userId, @RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPostsByUser(userId, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 10. 게시글 통합검색
     */
    @GetMapping("/posts/search")
    public ResponseEntity searchAllPosts(@RequestParam String keyword, @RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        log.info("시작!" + pageRequest.getOffset());
        result = postsService.searchAllByKeyword(keyword, pageRequest);
//        try {
//        } catch (Exception e) {
//            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
//        }
        log.info(result.toString());
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
