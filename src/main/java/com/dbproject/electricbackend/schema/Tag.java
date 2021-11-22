package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("标签")
@AllArgsConstructor
public class Tag {
    @Getter
    @ApiModelProperty("标签 ID")
    private final int id;

    @Getter
    @ApiModelProperty("标签名称")
    private final String name;
}
