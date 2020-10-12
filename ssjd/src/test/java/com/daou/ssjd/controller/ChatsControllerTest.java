package com.daou.ssjd.controller;

import com.daou.ssjd.domain.entity.Messages;
import com.daou.ssjd.dto.ChatsSendRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ChatsController chatsController;

    private final ObjectMapper mapper = new ObjectMapper();

    static final String WEBSOCKET_EP = "http://localhost:3000/ws";
    static final String WEBSOCKET_SEND_URI = "/api/chats/";
    static final String WEBSOCKET_TOPIC_URI = "/sub/receive/";

    private WebSocketStompClient stompClient;
    List<StompSession> socketSession;
    BlockingQueue<Messages> blockingQueue;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(chatsController)
                .alwaysExpect(status().isOk())
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();

        stompClient = new WebSocketStompClient(new SockJsClient(asList(new WebSocketTransport(new StandardWebSocketClient()))));

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        socketSession = new ArrayList<>(500);
        blockingQueue = new LinkedBlockingDeque<>();
    }

    @Test
    public void 메시지리스트받기_test() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/chats/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].messageId", is(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 구독_test() throws Exception{
        StompSession session = stompClient.connect(WEBSOCKET_EP, new StompSessionHandlerAdapter() {}).get(5, TimeUnit.SECONDS);
        StompSession.Subscription subscription = session.subscribe(WEBSOCKET_TOPIC_URI, new DefaultStompFrameHandler());
        Assert.assertNotNull(subscription.getSubscriptionId());
    }



    @Transactional
    @Test
    public void 메시지보내기_test() throws Exception{
        StompSession session = stompClient
                .connect(WEBSOCKET_EP, new StompSessionHandlerAdapter() {})
                .get(1, TimeUnit.SECONDS);
        StompSession.Subscription subscription = session.subscribe(WEBSOCKET_TOPIC_URI, new DefaultStompFrameHandler());

        try{
            session.send("/api/chats/1", new ChatsSendRequestDto(1, "hello", ChatsSendRequestDto.MessageType.SEND));
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }

        //테스트 작성중
        String msg = "This is test message in boot-test";
        ChatsSendRequestDto dto = new ChatsSendRequestDto(1, msg, ChatsSendRequestDto.MessageType.SEND);
        session.send(WEBSOCKET_SEND_URI + "1", dto);

        Assert.assertEquals(msg, blockingQueue.poll(1, TimeUnit.SECONDS));
    }

    class DefaultStompFrameHandler implements StompFrameHandler {

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return Messages.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            blockingQueue.offer((Messages) payload);
        }
    }

}
