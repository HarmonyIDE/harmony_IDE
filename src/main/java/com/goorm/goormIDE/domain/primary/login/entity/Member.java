package com.goorm.goormIDE.domain.primary.login.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq", nullable = false, unique = true)
    private Integer memberSeq;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name="phone_num")
    private String phoneNum;

    @Column(name = "admin_yn")
    private String adminYn;

    @Column(name = "reg_time")
    private LocalDateTime regTime;
}