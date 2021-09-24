package com.dbproject.electricbackend.exception;

import lombok.Getter;

/**
 * 统一自定义异常类, 作为业务逻辑层面上的异常
 */
public class CustomException extends Exception {
    @Getter
    private final int code;
    @Getter
    private final String message;

    private static final int CUSTOMIZE_EXCEPTION_CODE = 9000;

    /**
     * 私有构造方法, 禁止从外部实例化, 仅通过指定的工厂方法进行实例化.
     * @param code 异常码
     * @param message 异常信息
     */
    private CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CustomException defined(Define define) {
        return new CustomException(define.getCode(), define.getMessage());
    }

    public static CustomException customized(String message) {
        return new CustomException(CUSTOMIZE_EXCEPTION_CODE, message);
    }

    public enum Define {
        WRONG_USERNAME_PASSWORD(1001, "用户名或密码错误"),
        NEED_LOGIN_FIRST(1002, "请先登录"),
        INVALID_SESSION(1003, "会话无效, 请重新登录"),
        NON_EXIST_USER(1004, "该用户不存在"),
        INVALID_TOKEN(1005, "Token 无效")
        ;

        @Getter
        private final int code;
        @Getter
        private final String message;

        Define(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
