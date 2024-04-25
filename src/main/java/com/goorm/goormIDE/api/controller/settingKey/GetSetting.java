package com.goorm.goormIDE.api.controller.settingKey;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequiredArgsConstructor
public class GetSetting {

    @Value("${chat.url:}")
    private String webSocketUrl;
    @Value("${gpt.key:}")
    private String chatGptKey;

    @PostMapping("/get/wsUrl")
    public String chatSet() {
//        System.out.println("WebSocket URL passed: " + webSocketUrl);
        return webSocketUrl;
    }
    @PostMapping("/get/gptKey")
    public String gptKey() {
//        System.out.println("gptKey@@@ : " + chatGptKey);
        return chatGptKey;
    }
}

