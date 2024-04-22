package com.goorm.goormIDE.api.controller.join;

import com.goorm.goormIDE.core.dto.request.join.JoinDto;
import com.goorm.goormIDE.core.service.join.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity join(
            @RequestPart("joinData") JoinDto joinDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        joinService.join(joinDto, image);

        return ResponseEntity.ok("회원가입 성공");
    }
}
