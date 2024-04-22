package com.goorm.goormIDE.core.service.edit;

import com.goorm.goormIDE.core.dto.request.join.JoinDto;
import com.goorm.goormIDE.core.dto.request.join.UserDto;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EditService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Users findUserById(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자가 없음"));
    }

    @Transactional
    public void updateUser(String username, JoinDto joinDto) {
        Users user = findUserById(username);
        if (joinDto.getPassword() != null && !joinDto.getPassword().equals(joinDto.getCheckPassword())) {
            user.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        }
        user.setEmail(joinDto.getEmail());
        //userRepository.save(user);
    }
}