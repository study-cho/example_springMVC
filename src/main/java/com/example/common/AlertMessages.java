package com.example.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AlertMessages {
    JOIN_SUCCESS("가입이 완료되었습니다"),
    LOGIN_FAIL("로그인에 실패했습니다"),
    LOGIN_SUCCESS("로그인 되었습니다"),
    MODIFY_SUCCESS("수정되었습니다"),
    NOT_LOGIN("로그인해주세요");

    private final String message;

}
