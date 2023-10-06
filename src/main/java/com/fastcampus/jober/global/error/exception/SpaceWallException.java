package com.fastcampus.jober.global.error.exception;

import com.fastcampus.jober.global.constant.ErrorCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SpaceWallException extends DomainException {

    public SpaceWallException(ErrorCode errorCode) { super(errorCode); }

    public SpaceWallException(String message) { this(ErrorCode.BAD_SPACE_WALL_REQUEST); }
}
