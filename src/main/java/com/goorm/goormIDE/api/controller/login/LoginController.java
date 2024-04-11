package com.goorm.goormIDE.api.controller.login;

import com.goorm.goormIDE.core.dto.request.member.MemberDto;
import com.goorm.goormIDE.core.service.login.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "LoginController", description = "로그인 관련 API")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Operation(summary = "로그인", description = "사용자 로그인을 처리합니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("id") String id, @RequestParam("password") String password) {
        ResponseEntity<?> response = loginService.chkAdmin(id, password);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        } else {
            return ResponseEntity.badRequest().body("로그인 실패: 아이디 또는 비밀번호를 확인해주세요.");
        }
    }

    @Operation(summary = "회원가입", description = "회원을 등록합니다.")
    @PostMapping("/insMem")
    public ResponseEntity<String> insMem(@RequestBody MemberDto memberDto) {
        boolean isRegistered = loginService.registerUser(memberDto);

        if (isRegistered) {
            return ResponseEntity.ok("회원가입 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
        }
    }

    @Operation(summary = "로그아웃", description = "로그아웃 처리합니다.")
    @PostMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.ok("로그아웃 성공");
    }
}