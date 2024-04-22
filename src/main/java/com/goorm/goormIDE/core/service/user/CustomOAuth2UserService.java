package com.goorm.goormIDE.core.service.user;

import com.goorm.goormIDE.core.dto.*;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import com.goorm.goormIDE.domain.primary.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = null;

        if (registrationId.equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String username = oAuth2UserInfo.getProvider() + " " + oAuth2UserInfo.getProviderId();

        Optional<Users> findUser = userRepository.findByUsername(username);

        if (findUser.isEmpty()) {
            Users user = Users.builder()
                    .username(username)
                    .name(oAuth2UserInfo.getName())
                    .email(oAuth2UserInfo.getEmail())
                    .image(oAuth2UserInfo.getImage())
                    .role("ROLE_USER")
                    .build();
            userRepository.save(user);

            return new CustomOAuth2User(user);

        } else {
            Users user = findUser.get();
            user.setImage(oAuth2UserInfo.getImage());
            userRepository.save(user);

            return new CustomOAuth2User(user);

        }
    }
}
