package com.cigna.benefit.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketEventSessionManager implements WebSocketHandler  {

    @Autowired
    private WebSocketSessionManager handler;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        handler.addSession(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {}

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {}

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        handler.removeSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

