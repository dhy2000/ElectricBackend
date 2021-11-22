package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("标签操作请求体")
public class TagRequest {
    @Getter
    @ApiModelProperty("游戏ID")
    private int gameId;

    @Getter
    @ApiModelProperty("标签ID")
    private int tagId;

    @Getter
    @ApiModelProperty("操作种类, 0 为添加标签, 1 为去除标签, 其余值无效")
    private int type;
}
