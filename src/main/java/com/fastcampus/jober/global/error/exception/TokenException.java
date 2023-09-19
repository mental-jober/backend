package com.fastcampus.jober.global.error.exception;

import com.fastcampus.jober.global.constant.ErrorCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenException extends DomainException {

    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
