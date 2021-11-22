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

    /**
     * 私有构造方法, 禁止从外部实例化, 仅通过指定的静态方法进行实例化.
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

    public static CustomException customized(int code, String message) {
        return new CustomException(code, message);
    }

    public enum Define {
        WRONG_USERNAME_PASSWORD(1001, "用户名或密码错误"),
        NEED_LOGIN_FIRST(1002, "请先登录"),
        INVALID_SESSION(1003, "会话无效, 请重新登录"),
        NON_EXIST_USER(1004, "该用户不存在"),
        INVALID_TOKEN(1005, "Token 无效"),
        WRONG_OLD_PASSWORD(1006, "旧密码错误"),

        NON_EXIST_GAME(1101, "当前游戏不存在"),
        NOT_ENOUGH_BALANCE(1102, "玩家余额不足"),
        ILLEGAL_RECHARGE_AMOUNT(1103, "充值金额非法"),
        INVALID_ORDER(1104, "无效的订单号"),

        ALREADY_ONLINE(1111, "玩家已经在线，无法上线"),
        ALREADY_OFFLINE(1112, "玩家已经下线，无法上线"),
        ILLEGAL_BEHAVIOR(1113, "不合法的行为"),
        ALREADY_FRIEND(1114, "你们已经是好友，不可重复添加好友"),
        NOT_FRIEND(1115, "你们不是好友"),
        ACQUIRED_ACHIEVEMENT(1116, "成就已获得，不可重复获得"),
        ALREADY_HAS_TAG(1117, "标签已存在"),
        NOT_HAS_TAG(1118, "标签不存在"),

        FILE_CREATE_ERROR(2101, "无法创建文件"),
        FILE_ALREADY_EXIST(2102, "同名文件已存在"),
        FILE_NOT_EXIST(2103, "文件不存在"),
        INVALID_FILE_NAME(2104, "非法文件名"),
        IMAGE_ERROR(2105, "图片返回异常")
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
