package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("创建成就请求体")
@AllArgsConstructor
public class AchievementCreate {
    @Getter
    @ApiModelProperty("成就所属的游戏ID")
    private final int gameId;

    @Getter
    @ApiModelProperty("成就名称")
    private final String name;

    @Getter
    @ApiModelProperty("成就描述")
    private final String describe;
}
