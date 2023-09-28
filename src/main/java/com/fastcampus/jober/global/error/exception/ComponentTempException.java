package com.fastcampus.jober.global.error.exception;

import com.fastcampus.jober.global.constant.ErrorCode;

public class ComponentTempException extends DomainException{

    public ComponentTempException(ErrorCode errorCode) {super(errorCode);}

}
