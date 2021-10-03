package com.dbproject.electricbackend.schema;

import com.dbproject.electricbackend.exception.CustomException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("操作结果")
@Data
@AllArgsConstructor
public class StatusMessage {
    @Getter
    @ApiModelProperty("状态码, 0 为操作成功, 1-999 对应 HTTP 错误状态码, 1000 以上为自定义错误码")
    private final int code;

    @Getter
    @NonNull
    @ApiModelProperty("状态信息, 表示操作成功时可以为空串")
    private final String message;

    private static final int CATCH_EXCEPTION_CODE = 9000;

    private static final StatusMessage SUCCESSFUL_STATUS = new StatusMessage(0, "");

    public static StatusMessage successfulStatus() {
        return SUCCESSFUL_STATUS;
    }

    public static StatusMessage fromCustomException(CustomException e) {
        return new StatusMessage(e.getCode(), e.getMessage());
    }

    public static StatusMessage fromThrowable(Throwable th) {
        return new StatusMessage(CATCH_EXCEPTION_CODE,
                "Catch " + th.getClass().getName() + ": " + th.getMessage());
    }
}
