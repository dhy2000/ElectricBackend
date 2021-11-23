package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@ApiModel("游戏扩展包创建请求")
public class GameModCreate {
    @Getter
    @NonNull
    @ApiModelProperty("扩展包名称")
    private final String name;

    @Getter
    @NonNull
    @ApiModelProperty("扩展包描述")
    private final String describe;

    @Getter
    @ApiModelProperty("所属于的游戏ID")
    private final int gameId;
}
