package com.fastcampus.jober.global.exception.advice;

import com.fastcampus.jober.global.exception.Exception401;
import com.fastcampus.jober.global.exception.Exception403;
import com.fastcampus.jober.global.exception.Exception500;
import com.fastcampus.jober.global.exception.ExceptionValid;
import com.fastcampus.jober.global.utils.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(Exception e) {

        ApiUtils.ApiResult<?> apiResult =
            ApiUtils.error(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodValidException(MethodArgumentNotValidException e) {
        ExceptionValid ev = new ExceptionValid(
            e.getBindingResult().getFieldError().getCode(),
            e.getBindingResult().getFieldError().getDefaultMessage()
        );
        return new ResponseEntity<>(ev.body(), ev.status());
    }
}
