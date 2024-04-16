package com.goorm.goormIDE.core.dto.request.join;

import lombok.Data;

@Data
public class JoinDto {
    private String username;

    private String password;

    private String checkPassword;

    private String name;

    private String email;
}
