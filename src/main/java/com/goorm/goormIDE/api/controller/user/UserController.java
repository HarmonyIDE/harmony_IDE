package com.goorm.goormIDE.api.controller.user;

import com.goorm.goormIDE.core.dto.request.join.UserDto;
import com.goorm.goormIDE.core.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/user")
    public ResponseEntity findUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username = " + username);
        UserDto userDto = userService.findByUsername(username);
        return ResponseEntity.ok(userDto);
    }
}
