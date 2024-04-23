package com.goorm.goormIDE.chat.chatsearch;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Message {
    private String sender;
    private String content;
    private String name; // 닉네임 필드 추가
    private Date timestamp;

    // 생성자 수정
    public Message(String sender, String content, String name) {
        this.sender = sender;
        this.content = content;
        this.name = name; // Correctly initialize the name
        this.timestamp = new Date(); // Current time for the timestamp
    }

}
