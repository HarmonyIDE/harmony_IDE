package com.goorm.goormIDE.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors
                         .configurationSource(corsConfigurationSource())
                )
                .csrf((csrfConfigurer)->
                        csrfConfigurer.disable()
                )
                .authorizeHttpRequests((autorizeRequests) ->
                        autorizeRequests
                                .requestMatchers("/","/index.html","/login/**").permitAll()
                                //TODO swagger-ui 경로는 마스터계정(개발자)만 들어갈수 있도록 처리 필요
                                .requestMatchers("/swagger-ui/**","/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
//                                .requestMatchers("/user").hasRole(Role.USER.name())
//                                .requestMatchers("/admin").hasRole(Role.ADMIN.name())
                                //TODO 페이지별 권한 주기 개발단계에선 모든 페이지 권한 열어둠.
//                                .anyRequest().authenticated()
                                .anyRequest().permitAll()
                );
        return http.build();
    }

    //개발완료 후 닫아야함 cors 처리
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}