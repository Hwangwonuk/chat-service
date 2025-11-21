package com.example.chatservice.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

    // 웹 소켓 클라이언트가 서버에 연결한 뒤 실행될 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} connected", session.getId());

        this.webSocketSessionMap.put(session.getId(), session);
    }

    // 클라이언트에서 메세지가 왔을 때 처리하는 메서드
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("{} sent {}", session.getId(), message.getPayload());

        this.webSocketSessionMap.values().forEach(
                webSocketSession -> {
                    try {
                        webSocketSession.sendMessage(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    // 연결이 끝난 뒤에 실행될 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} disConnected", session.getId());

        this.webSocketSessionMap.remove(session.getId());
    }

}
