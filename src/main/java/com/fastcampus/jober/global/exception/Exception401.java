package com.fastcampus.jober.global.exception;


import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;


// 인증 안됨
@Getter
public class Exception401 extends RuntimeException {

    public Exception401(String message) {
        super(message);
    }

    public ResponseDTO<?> body() {
        return new ResponseDTO<>(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            getMessage());
    }

    public HttpStatus status() {
        return HttpStatus.UNAUTHORIZED;
    }
}