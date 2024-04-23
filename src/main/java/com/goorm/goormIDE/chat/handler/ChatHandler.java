package com.goorm.goormIDE.chat.handler;// Import 필요한 패키지
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 JSON 메시지를 받음
        String payload = message.getPayload();
        Message chatMessage = objectMapper.readValue(payload, Message.class);  // JSON 문자열을 Message 객체로 변환

        // JSON에서 추출한 사용자 이름으로 메시지 객체 설정
        chatMessage.setSender(chatMessage.getSender());

        // 메시지 객체를 JSON 문자열로 다시 변환하여 모든 클라이언트에게 전송
        String messageJson = objectMapper.writeValueAsString(chatMessage);
        for (WebSocketSession sess : sessions) {
            sess.sendMessage(new TextMessage(messageJson));
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}

// 메시지 객체
class Message {
    private String sender;
    private String content;

    // Getter와 Setter
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
