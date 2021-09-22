package com.dbproject.electricbackend.model.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("操作结果返回")
@Data
public class StatusMessage {
    @Getter
    @NonNull
    private final int code;

    @Getter
    @NonNull
    private final String message;

    public StatusMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private static final StatusMessage successfulStatus = new StatusMessage(0, "");
}
