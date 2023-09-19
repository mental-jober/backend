package com.fastcampus.jober.global.error.exception;

import com.fastcampus.jober.global.constant.ErrorCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyTemplateException extends DomainException {

    public MyTemplateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
