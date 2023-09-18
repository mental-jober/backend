package com.fastcampus.jober.global.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ApiUtil {

    public static <T> Response<T> result(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response<T> {

        private final int code;
        private final String message;
        private final T data;
    }
}
