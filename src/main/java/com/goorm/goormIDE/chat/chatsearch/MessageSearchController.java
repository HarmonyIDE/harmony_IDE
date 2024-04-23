package com.goorm.goormIDE.chat.chatsearch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageSearchController {

    @GetMapping("/search")
    public ResponseEntity<List<Message>> search(@RequestParam String query) {
        List<Message> results = MessageStorage.searchMessages(query);
        return ResponseEntity.ok(results);
    }
}
