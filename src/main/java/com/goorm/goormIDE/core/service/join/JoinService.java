package com.goorm.goormIDE.core.service.join;

import com.goorm.goormIDE.core.dto.request.join.JoinDto;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.dir}")
    private String fileDir;

    public void join(JoinDto joinDto, MultipartFile image) {
        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        boolean existUser = userRepository.existsByUsername(username);

        if (existUser) {
            return;
        }

//        if (!password.equals(joinDto.getCheckPassword())) {
//            // TODO: 비밀번호와 비밀번호 확인이 다를 때, 로직 작성
//            log.info(password);
//            log.info(joinDto.getCheckPassword());
//            return ;
//        }

        // 이미지 저장
        String fileName = "";
        if (image != null && !image.isEmpty()) {
            fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            String fullPath = fileDir + File.separator + fileName;
            try {
                image.transferTo(new File(fullPath));
                System.out.println("fullPath = " + fullPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            fileName = "default_image.jpeg";
        }

        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/")
                .path(fileName)
                .toUriString();

        Users user = Users.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .image(imageUrl)
                .role("ROLE_USER")
                .build();

        if(image==null){
            user.setImage("default");
        }
        userRepository.save(user);
    }
}
