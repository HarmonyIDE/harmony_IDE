package com.goorm.goormIDE.core.service.edit;

import com.goorm.goormIDE.core.common.response.ApiResponse;
import com.goorm.goormIDE.core.dto.request.join.JoinDto;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

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
    public ApiResponse updateUser(JoinDto joinDto) {
        try {
            Users user = userRepository.findByUsername(joinDto.getUsername())
                    .orElse(null);

            if (user == null) {
                return ApiResponse.failure("이 에러는 뜰리가 없지만 그래도 뭐");
            }

            // oldPassword 검증
            if (!passwordEncoder.matches(joinDto.getOldPassword(), user.getPassword())) {
                return ApiResponse.failure("현재 비밀번호가 일치하지 않습니다.");
            }

            // password와 checkPassword 일치 검증
            if (!joinDto.getPassword().equals(joinDto.getCheckPassword())) {
                return ApiResponse.failure("변경하려는 비밀번호를 일치되게 작성해주세요.");
            }

            // 이미지 설정
            if (joinDto.getImage() == null || joinDto.getImage().isEmpty()) {
                user.setImage("default"); //이미지없을때 default 저장
            } else {
                user.setImage(joinDto.getImage());
            }

            user.setPassword(passwordEncoder.encode(joinDto.getPassword()));
            user.setName(joinDto.getName());
            user.setEmail(joinDto.getEmail().trim());

            userRepository.save(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponse.success("회원 정보 수정이 완료되었습니다.");
    }
}