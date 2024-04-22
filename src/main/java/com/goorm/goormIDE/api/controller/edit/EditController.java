package com.goorm.goormIDE.api.controller.edit;

import com.goorm.goormIDE.core.dto.request.join.JoinDto;
import com.goorm.goormIDE.core.service.edit.EditService;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class EditController {
    private final EditService editService;

    @Autowired
    public EditController(EditService editService) {
        this.editService = editService;
    }
    @GetMapping("/memberInfo/{username}")
    public ResponseEntity<Users> getUserInfo(@PathVariable String username){
        Users user = editService.findUserById(username);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/memberInfo/{username}")
    public ResponseEntity<String> editUser(@RequestBody JoinDto joinDto,
                                           @PathVariable String username) {

        editService.updateUser(username,joinDto);
        return ResponseEntity.ok("수정완료");
    }
}
