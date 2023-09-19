package com.fastcampus.jober.global.error.exception;

import com.fastcampus.jober.global.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DomainException extends RuntimeException {

    private HttpStatus httpStatus;
    private int code;
    private String message;

    public DomainException(ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.code = errorCode.getStatusCode();
        this.message = errorCode.getMessage();
    }
}
