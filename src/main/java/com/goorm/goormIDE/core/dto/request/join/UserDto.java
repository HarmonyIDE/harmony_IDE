package com.goorm.goormIDE.core.dto.request.join;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String username;

    private String name;

    private String email;

    private String image;

    private String role;
}
