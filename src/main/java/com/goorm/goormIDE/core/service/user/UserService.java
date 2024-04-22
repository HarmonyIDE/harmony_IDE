package com.goorm.goormIDE.core.service.user;

import com.goorm.goormIDE.core.dto.request.join.UserDto;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto findById(Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        return UserDto.of(user);
    }
}
