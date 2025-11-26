package com.example.chatservice.controller;

import com.example.chatservice.dtos.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Slf4j
@Controller
public class StompChatController {

    /**
     * STOMP Message 를 다룰 Controller 메서드
     *
     * @param message 클라이언트가 입력한 message
     * @return
     * @MessageMapping 어떤 경로로 publish된 Message를 라우팅 할것인지 지정
     * /pub/chats 로 발행된 메세지는 해당 컨트롤러로 전달
     * @SendTo 에서 지정된 경로의 구독자들에게 메세지를 전달
     */
    @MessageMapping("/chats/{chatroomId}")
    @SendTo("/sub/chats/{chatroomId}")
    public ChatMessage handleMessage(@AuthenticationPrincipal Principal principal, @DestinationVariable Long chatroomId, @Payload Map<String, String> payload) {
        log.info("{} sent {} in {}", principal.getName(), payload, chatroomId);

        return new ChatMessage(principal.getName(), payload.get("message"));
    }

}
