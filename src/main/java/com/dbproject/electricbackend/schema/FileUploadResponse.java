package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("文件上传结果反馈")
@Data
public class FileUploadResponse {

    @Getter
    @ApiModelProperty("操作状态码, 0 为成功, 非 0 为失败")
    private final int code;

    @Getter
    @NonNull
    @ApiModelProperty("文件名称")
    private final String name;

    @Getter
    @ApiModelProperty("下载地址")
    private final String url;

    @Getter
    @ApiModelProperty("文件类型")
    private final String type;

    @Getter
    @ApiModelProperty("文件大小")
    private final long size;

    @Getter
    @ApiModelProperty("错误原因(如果操作状态码非零则有)")
    private final String message;

    public FileUploadResponse(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.code = 0;
        this.message = null;
    }

    public FileUploadResponse(String name, int code, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
        this.url = null;
        this.type = null;
        this.size = 0;
    }
}
