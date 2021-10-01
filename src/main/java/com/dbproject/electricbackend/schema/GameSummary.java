package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("游戏简介，用来在游戏商城页面展示")
@AllArgsConstructor
public class GameSummary {
    @Getter
    @ApiModelProperty("游戏 ID（不展示，前端需要保存以进行后续可能的请求）")
    private final Integer id;

    @Getter
    @ApiModelProperty("游戏名称")
    private final String name;

    @Getter
    @ApiModelProperty("游戏价格（单位为人民币元）")
    private final Integer price;

    @Getter
    @ApiModelProperty("是否为多人联机游戏")
    private final boolean multiPlayer;
}
