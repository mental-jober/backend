package com.fastcampus.jober.global.constant;

public enum ErrorCode {

    INVALID_REQUEST("잘못된 요청입니다"),
    INVALID_ID("유효하지 않은 id값입니다"),
    INVALID_AUTHENTICATION("인증되지 않았습니다"),
    INVALID_ACCESS("잘못된 접근입니다"),
    INVALID_USER("권한이 없습니다"),

    EMPTY_ID("아이디를 입력해주세요"),
    EMPTY_PASSWORD("비밀번호를 입력해주세요"),

    EXISTING_EMAIL("이미 존재하는 이메일입니다"),
    CHECK_ID_PASSWORD("아이디와 비밀번호를 확인해주세요"),
    CHECK_ID("아이디를 확인해주세요"),
    CHECK_PASSWORD("비밀번호를 확인해주세요");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
