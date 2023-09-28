package com.fastcampus.jober.global.error.exception;

import com.fastcampus.jober.global.constant.ErrorCode;

public class ComponentException extends DomainException{

    public ComponentException(ErrorCode errorCode) {super(errorCode);}

}
