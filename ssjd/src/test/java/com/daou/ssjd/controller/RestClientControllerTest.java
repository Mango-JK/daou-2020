package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.service.PostsService;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(PostsController.class)
public class RestClientControllerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private PostsService postsService;
    @Autowired
    private MockRestServiceServer server;

    @Test
    public void rest_test() {
        server.expect(requestTo("/api/posts/42"))
                .andRespond(
                        withSuccess(new ClassPathResource("/test.json", getClass()), MediaType.APPLICATION_JSON));

        Posts post = postsService.findByPostId(42).get();

        Assertions.assertThat(post.getLanguage()).isEqualTo("java");
        Assertions.assertThat(post.getUser().getUserId()).isEqualTo(15);
    }
}
