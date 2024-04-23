package com.goorm.goormIDE.core.service.user;

import com.goorm.goormIDE.core.dto.request.join.CustomUserDetails;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // TODO: 유저가 없을 때, 로직 구현
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new CustomUserDetails(user);

    }
}
