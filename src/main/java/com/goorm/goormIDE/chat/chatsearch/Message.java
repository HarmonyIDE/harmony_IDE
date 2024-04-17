package com.goorm.goormIDE.chat.chatsearch;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Message {
    private String sender;
    private String content;
    private Date timestamp;

    public Message(String sender, String content, Date timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

}
