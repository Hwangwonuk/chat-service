package com.example.chatservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class StompChatController {

    /**
     * STOMP Message 를 다룰 Controller 메서드
     * @MessageMapping 어떤 경로로 publish된 Message를 라우팅 할것인지 지정
     * /pub/chats 로 발행된 메세지는 해당 컨트롤러로 전달
     * @SendTo 에서 지정된 경로의 구독자들에게 메세지를 전달
     * @param message 클라이언트가 입력한 message
     * @return
     */
    @MessageMapping("/chats")
    @SendTo("/sub/chats")
    public String handleMessage(@Payload String message) {
        log.info("{} received", message);

        return message;
    }

}
