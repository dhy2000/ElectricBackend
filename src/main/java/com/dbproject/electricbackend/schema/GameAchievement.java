package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("游戏成就(属于某个游戏)")
@AllArgsConstructor
public class GameAchievement {
    @Getter
    @ApiModelProperty("成就名称")
    private final String name;

    @Getter
    @ApiModelProperty("成就详细描述, 可能为空字符串(前端需要做相应展示处理)")
    private final String describe;
}
