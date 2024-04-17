package com.goorm.goormIDE.chat.handler;

import com.goorm.goormIDE.chat.chatsearch.Message;
import com.goorm.goormIDE.chat.chatsearch.MessageStorage;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        log.info("payload : " + payload);

        // Create a new Message object and store it
        Message msg = new Message(session.getId(), payload, new Date());
        MessageStorage.addMessage(msg);

        // Send the message to all connected sessions
        for (WebSocketSession sess : list) {
            sess.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
        log.info("{} client connected", session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        list.remove(session);
        log.info("{} client disconnected", session);
    }
}
