package com.goorm.goormIDE.chat.controller;

import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
//@RequiredArgsConstructor

public class ChatController {

    //private final UserRepository userRepository;

    @GetMapping("/chat")
    public String chatGET() {
        log.info("@ChatController, chat GET()");
        return "chat"; // chat.html이 resources/templates에 있을 경우
    }
//    @GetMapping("/chat")
//    public String chat(Model model, @AuthenticationPrincipal Users user) {
//        if (user != null) {
//            model.addAttribute("username", user.getUsername());
//            log.info("login username : {}", user.getUsername());
//        }
//        return "chat";
//    }
}
