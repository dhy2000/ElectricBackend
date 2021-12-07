package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@Data
@ApiModel("游戏扩展包(MOD)")
@AllArgsConstructor
public class GameMod {
    @Getter
    @ApiModelProperty("扩展包ID")
    private final int id;

    @Getter
    @NonNull
    @ApiModelProperty("扩展包名称")
    private final String name;

    @Getter
    @NonNull
    @ApiModelProperty("扩展包描述")
    private final String describe;

    @Getter
    @ApiModelProperty("扩展包作者ID")
    private final int authorId;

    @Getter
    @NonNull
    @ApiModelProperty("扩展包作者昵称")
    private final String authorNickname;

    @Getter
    @NonNull
    @ApiModelProperty("创建时间")
    private final Date createTime;

    @Getter
    @NonNull
    @ApiModelProperty("最近更新时间")
    private final Date updateTime;

    @Getter
    @ApiModelProperty("所属游戏ID")
    private final int gameId;

    @Getter
    @ApiModelProperty("所属游戏名称")
    private final String gameName;
}
