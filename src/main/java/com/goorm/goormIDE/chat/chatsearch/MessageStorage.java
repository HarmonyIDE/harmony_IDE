package com.goorm.goormIDE.chat.chatsearch;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class MessageStorage {
    private static final int MAX_MESSAGES = 1000;  // 최대 메시지 수
    private static final List<Message> messages = new CopyOnWriteArrayList<>();

    public static synchronized void addMessage(Message message) {
        if (messages.size() >= MAX_MESSAGES) {
            messages.remove(0);  // 가장 오래된 메시지 삭제
        }
        messages.add(message);
    }

    public static List<Message> searchMessages(String query) {
        return messages.stream()
                .filter(m -> m.getContent().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
