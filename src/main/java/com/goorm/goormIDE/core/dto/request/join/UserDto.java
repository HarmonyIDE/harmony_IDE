package com.goorm.goormIDE.core.dto.request.join;

import com.goorm.goormIDE.domain.primary.login.entity.Users;
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

    public static UserDto of(Users user) {

        return UserDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .image(user.getImage())
                .role(user.getRole())
                .build();
    }
}
