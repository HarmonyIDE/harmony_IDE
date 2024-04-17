package com.goorm.goormIDE.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/chat")
    public String chatGET(){
        log.info("@ChatController, chat GET()");
        return "chat"; // chat.html이 resources/templates에 있을 경우
    }

}
