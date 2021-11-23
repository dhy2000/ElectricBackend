package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;

@Data
@ApiModel("某个用户的一条玩某游戏的记录")
@AllArgsConstructor
public class GamePlayRecord {
    @Getter
    @ApiModelProperty("游戏 ID")
    private final int gameId;

    @Getter
    @ApiModelProperty("游戏名称")
    private final String gameName;

    @Getter
    @ApiModelProperty("上线时间")
    private final Timestamp startTime;

    @Getter
    @ApiModelProperty("下线时间")
    private final Timestamp endTime;

    @Getter
    @ApiModelProperty("单次游戏时长(秒)")
    private final int duration;
}
