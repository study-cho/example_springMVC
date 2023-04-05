package com.example.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AlertMessages {
    JOIN_SUCCESS("가입이 완료되었습니다"),
    LOGIN_FAIL("로그인에 실패했습니다"),
    LOGIN_SUCCESS("로그인 되었습니다"),
    LOGOUT("로그아웃 되었습니다"),
    NOT_LOGIN("로그인해주세요"),

    MODIFY_SUCCESS("수정되었습니다"),

    NOT_WRITER("잘못된 접근입니다"),
    WRITE_SUCCESS("저장되었습니다"),
    DELETE("삭제되었습니다")

    ;

    private final String message;

}
