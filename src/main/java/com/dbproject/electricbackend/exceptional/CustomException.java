package com.dbproject.electricbackend.exceptional;

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

    public static CustomException fromDefinedTable(ExceptionDefine define) {
        return new CustomException(define.getCode(), define.getMessage());
    }

    public static CustomException customized(String message) {
        return new CustomException(CUSTOMIZE_EXCEPTION_CODE, message);
    }
}
