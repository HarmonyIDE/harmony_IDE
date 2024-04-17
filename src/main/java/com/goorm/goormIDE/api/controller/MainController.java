package com.goorm.goormIDE.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // 기본페이지를 요청 메소드
    @GetMapping("/")
    public String index() {
        return "index"; // => templates 폴더의 index.html을 찾아감
    }

}
