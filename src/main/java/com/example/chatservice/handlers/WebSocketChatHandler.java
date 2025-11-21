package com.example.chatservice.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class WebSocketChatHandler extends TextWebSocketHandler {

    // 웹 소켓 클라이언트가 서버에 연결한 뒤 실행될 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} connected", session.getId());
    }

    // 클라이언트에서 메세지가 왔을 때 처리하는 메서드
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    // 연결이 끝난 뒤에 실행될 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} disConnected", session.getId());
    }

}
