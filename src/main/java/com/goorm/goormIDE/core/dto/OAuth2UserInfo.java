package com.goorm.goormIDE.core.dto;

public interface OAuth2UserInfo {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

    String getImage();
}
