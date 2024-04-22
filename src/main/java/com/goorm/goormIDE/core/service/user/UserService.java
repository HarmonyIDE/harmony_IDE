package com.goorm.goormIDE.core.service.user;

import com.goorm.goormIDE.core.dto.request.join.UserDto;
import com.goorm.goormIDE.domain.primary.login.entity.User;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserDto.of(user);
    }
}
