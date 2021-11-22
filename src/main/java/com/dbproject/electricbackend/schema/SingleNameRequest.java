package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("单名称请求体")
public class SingleNameRequest {
    @Getter
    @ApiModelProperty("名称")
    private String name;
}
