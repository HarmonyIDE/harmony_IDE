package com.goorm.goormIDE.api.controller.settingKey;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GetSetting {

    @Value("${chat.url:ws://localhost:8080/ws/chat}")
    private String webSocketUrl;
    @Value("${gpt.key:aaaa}")
    private String chatGptKey;

    @PostMapping("/get/wsUrl")
    public ResponseEntity<Map<String, String>> chatSet() {
        Map<String, String> response = new HashMap<>();
        System.out.println(webSocketUrl+">>>>>>>>>>");
        response.put("url", webSocketUrl);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/get/gptKey")
    public ResponseEntity<Map<String, String>> gptKey() {
        Map<String, String> response = new HashMap<>();
        response.put("key", chatGptKey);
        System.out.println("gptKey@@@ : " + chatGptKey);
        return ResponseEntity.ok(response);
    }
}

