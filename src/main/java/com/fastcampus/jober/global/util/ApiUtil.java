package com.fastcampus.jober.global.util;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ApiUtil {

    public static <T> ApiResponse<T> result(int code, String message, @Nullable T data) {
        return new ApiResponse<>(code, message, data);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApiResponse<T> {

        private final int code;
        private final String message;
        private final T data;
    }
}
