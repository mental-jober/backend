package com.fastcampus.jober.core.exception;

import com.fastcampus.jober.core.dto.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }

    public ResponseDTO<?> body(){
        return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), getMessage());
    }

    public HttpStatus status(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
