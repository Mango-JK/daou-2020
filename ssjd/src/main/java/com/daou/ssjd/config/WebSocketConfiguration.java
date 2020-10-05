package com.daou.ssjd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    //set endPoint
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    public void configureMessageBroker(MessageBrokerRegistry registry){
        //routing -> @MessageMapping
        registry.setApplicationDestinationPrefixes("/chat");

        //cilent에게 message 전달
        registry.enableSimpleBroker("/sub");
    }
}
