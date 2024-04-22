package com.goorm.goormIDE.domain.primary.login.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    //DB 상 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Login id
    private String username;

    private String password;

    // 실제 이름
    private String name;

    private String email;

    private String image;

    private String role;

    @Builder
    private Users(Long id, String username, String password, String name, String email, String image, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.image = image;
        this.role = role;
    }

    public void setImage(String image) {
        this.image = image;
    }
}