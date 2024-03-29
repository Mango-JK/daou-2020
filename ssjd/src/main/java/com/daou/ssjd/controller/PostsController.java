package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.dto.PostsResponseDto;
import com.daou.ssjd.dto.PostsSaveRequestDto;
import com.daou.ssjd.dto.PostsUpdateRequestDto;
import com.daou.ssjd.service.PostsService;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;

@CrossOrigin(origins = "*")
@Slf4j
@Api(tags = "Posts")
@RequiredArgsConstructor
@RequestMapping("/api/")
@RestController
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public ResponseEntity savePosts(@RequestBody PostsSaveRequestDto requestDto) throws Exception {
        PostsResponseDto result = null;
        try {
            result = new PostsResponseDto(postsService.savePost(requestDto));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity updatePosts(@PathVariable("postId") int postId, @RequestBody PostsUpdateRequestDto requestDto) throws Exception {
        try {
            postsService.updatePost(postId, requestDto);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(postsService.findByPostId(postId).get(), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePosts(@PathVariable("postId") int postId) throws Exception {
        try {
            postsService.deletePost(postId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(postId, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity findOne(@PathVariable("postId") int postId) {
        Posts dto = postsService.findByPostId(postId).get();
        if (dto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity findAllPosts(@RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPosts(pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
    }

    @GetMapping("/posts/language/{language}")
    public ResponseEntity findAllByLanguage(@PathVariable("language") String language, @RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPostsByLanguage(language, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getContent(), HttpStatus.OK);
    }

    @GetMapping("/posts/problem/{problemSite}")
    public ResponseEntity findAllByPlatform(@PathVariable("problemSite") String problemSite, @RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPostsByProblemSite(problemSite, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getContent(), HttpStatus.OK);
    }

    @GetMapping("/posts/problem/language")
    public ResponseEntity findAllByPlatformAndLanguage(@RequestParam("language") String language, @RequestParam("problemSite") String problemSite, int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPostsByLanguageAndProblemSite(language, problemSite, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getContent(), HttpStatus.OK);
    }

    @GetMapping("/posts/users/{userId}")
    public ResponseEntity findAllByUser(@PathVariable("userId") int userId, @RequestParam("pageNum") int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;
        try {
            result = postsService.findAllPostsByUser(userId, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getContent(), HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity searchAllPosts(@PathVariable @Nullable String keyword, @RequestParam int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;

        try {
            result = postsService.searchAllByKeyword(keyword, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        result = postsService.searchAllByKeyword(keyword, pageRequest);
        return new ResponseEntity(result.getContent(), HttpStatus.OK);
    }

    @GetMapping("/posts/search/problem/language")
    public ResponseEntity searchAllByPlatform(@RequestParam @Nullable String language, @RequestParam @Nullable String problemSite, @RequestParam @Nullable String keyword, @RequestParam int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 6, Sort.by("modifiedDate").descending());
        Page<Posts> result = null;

        try {
            result = postsService.searchAllByPlatform(language, problemSite, keyword, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result.getContent(), HttpStatus.OK);
    }

    @GetMapping("posts/number/{userId}")
    public ResponseEntity countPostsByUserId(@PathVariable("userId") int userId) {
        Long postCounts = 0L;
        try {
            postCounts = postsService.countById(userId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(postCounts, HttpStatus.OK);
    }
}
