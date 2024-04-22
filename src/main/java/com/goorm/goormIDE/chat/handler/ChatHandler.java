package com.goorm.goormIDE.chat.handler;

import com.goorm.goormIDE.chat.chatsearch.Message;
import com.goorm.goormIDE.chat.chatsearch.MessageStorage;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Log4j2
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();
    //private static Map<WebSocketSession, String> sessionUserMap  = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //String nickName = sessionUserMap.get(session); //추가
        //String payload = nickName + ": " + message.getPayload();//추가
        String payload = message.getPayload();
        log.info("payload : " + payload);

        // Create a new Message object and store it
        Message msg = new Message(session.getId(), payload, new Date());
        MessageStorage.addMessage(msg);
        ;

         //Send the message to all connected sessions
        for (WebSocketSession sess : list) {
            sess.sendMessage(message);
        }
//        for (WebSocketSession sess : sessionUserMap.keySet()) {
//            sess.sendMessage(new TextMessage(payload));
//        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Users user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        log.info("{} client connected", session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        list.remove(session);
//        sessionUserMap.remove(session);
        log.info("{} client disconnected", session);
    }
}
