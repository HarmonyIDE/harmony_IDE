package com.goorm.goormIDE.core.common.response;

public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;

    public ApiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters and setters 생략

    public static ApiResponse success(String message) {
        return new ApiResponse(true, message,null);
    }

    public static ApiResponse failure(String message) {
        return new ApiResponse(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }
}