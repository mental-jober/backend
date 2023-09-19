package com.fastcampus.jober.global.error.exception;

import com.fastcampus.jober.global.constant.ErrorCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TemplateException extends DomainException {

    public TemplateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
