package com.goorm.goormIDE.core.dto.request.member;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private Long memberSeq;
    private String userId;
    private String password;
    private String adminYn;
    private String phoneNum;
    private LocalDateTime reg_time;
}
