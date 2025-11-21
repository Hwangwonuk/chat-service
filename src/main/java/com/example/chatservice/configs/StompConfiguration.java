package com.example.chatservice.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class StompConfiguration implements WebSocketMessageBrokerConfigurer {


    // 웹소켓 클라이언트가 어떤 경로(엔드포인트)로 서버에 접근해야 하는지 지정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chats");
    }

    // 메세지 브로커로서의 역할을 수행하기 위해 클라이언트에서 메세지를 발행하고, 브로커로부터 전달되는 메세지를 받기위해 구독신청 경로 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // pub url 지정
        registry.setApplicationDestinationPrefixes("/pub");
        // sub url 지정
        registry.enableSimpleBroker("/sub");
    }
}
