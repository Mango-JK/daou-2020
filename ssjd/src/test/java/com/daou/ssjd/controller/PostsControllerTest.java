package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.service.PostsService;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(PostsService.class)
class PostsControllerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private PostsService postsService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    void findAllPosts() {
        // given
        server.expect(requestTo("localhost:8080/api/v1/posts"))
                .andRespond(
                        withSuccess(new ClassPathResource("/test.json", getClass()), MediaType.APPLICATION_JSON));
        // when
        Posts post = postsService.findByPostId(1).get();

        Assertions.assertThat(post.getPostId()).isEqualTo(1);
        Assertions.assertThat(post.getUser()).isNotNull();
    }
}