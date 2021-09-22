package com.dbproject.electricbackend.exceptional;

import lombok.Getter;

public enum ExceptionDefine {
    WRONG_USERNAME_PASSWORD(1001, "用户名或密码错误"),
    NEED_LOGIN_FIRST(1002, "请先登录"),
    INVALID_SESSION(1003, "会话无效, 请重新登录")
    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    ExceptionDefine(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
