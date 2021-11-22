package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("用户上线或下线游戏的行为")
@AllArgsConstructor
public class GameBehavior {
    @Getter
    @ApiModelProperty("用户 ID, 必须是当前已经登录的用户")
    private final int userId;

    @Getter
    @ApiModelProperty("游戏 ID")
    private final int gameId;

    @Getter
    @ApiModelProperty("行为种类, 0 表示上线, 1 表示下线, 其余值不合法")
    private final int type;
}
