package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("单 ID 请求体")
public class SingleIdRequest {
    @Getter
    @ApiModelProperty("ID")
    private int id;
}
