package com.dbproject.electricbackend.exception;

import lombok.Getter;

public enum ExceptionDefine {
    WRONG_USERNAME_PASSWORD(1001, "用户名或密码错误"),
    NEED_LOGIN_FIRST(1002, "请先登录"),
    INVALID_SESSION(1003, "会话无效, 请重新登录"),
    NON_EXIST_USER(1004, "该用户不存在")
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
