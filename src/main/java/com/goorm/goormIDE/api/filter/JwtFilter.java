package com.goorm.goormIDE.api.filter;

import com.goorm.goormIDE.api.util.JwtUtil;
import com.goorm.goormIDE.core.dto.CustomOAuth2User;
import com.goorm.goormIDE.core.dto.request.join.CustomUserDetails;
import com.goorm.goormIDE.core.dto.request.join.UserDto;
import com.goorm.goormIDE.domain.primary.login.entity.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

//        if (authorization == null || !authorization.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        String token = null;
        if (authorization == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("Authorization")) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        } else {
            if (!authorization.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            token = authorization.split(" ")[1];
        }

        if (token == null || jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        Users user = Users.builder()
                .username(username)
                .password("temppassword")
                .role(role)
                .build();

        if (username.split(" ")[0].equals("google") || username.split(" ")[0].equals("kakao") || username.split(" ")[0].equals("naver")) {
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(user);
            Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
        } else {
            CustomUserDetails customUserDetails = new CustomUserDetails(user);
            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
        }
    }
}
