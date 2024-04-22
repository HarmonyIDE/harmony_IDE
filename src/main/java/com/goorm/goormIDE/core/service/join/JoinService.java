package com.goorm.goormIDE.core.service.join;

import com.goorm.goormIDE.core.dto.request.join.JoinDto;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinDto joinDto) {
        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        boolean existUser = userRepository.existsByUsername(username);

        if (existUser) {
            // TODO: 회원이 이미 존재했을 때, 로직 작성
            return;
        }

        if (!password.equals(joinDto.getCheckPassword())) {
            // TODO: 비밀번호와 비밀번호 확인이 다를 때, 로직 작성
            return ;
        }

        Users user = Users.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
    }
}
