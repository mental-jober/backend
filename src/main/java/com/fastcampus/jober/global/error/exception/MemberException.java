package com.fastcampus.jober.global.error.exception;

import com.fastcampus.jober.global.constant.ErrorCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberException extends DomainException {

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
