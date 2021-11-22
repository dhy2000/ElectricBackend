package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;

@Data
@ApiModel("当前用户获得游戏成就记录")
@AllArgsConstructor
public class GameAchievementAcquirement {
    @Getter
    @ApiModelProperty("游戏 ID")
    private final int gameId;

    @Getter
    @ApiModelProperty("游戏名称")
    private final String gameName;

    @Getter
    @ApiModelProperty("成就 ID")
    private final int achievementId;

    @Getter
    @ApiModelProperty("成就名称")
    private final String achievementName;

    @Getter
    @ApiModelProperty("成就描述")
    private final String describe;

    @Getter
    @ApiModelProperty("获得时间")
    private final Date acquireTime;
}
