package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.Collection;
import java.util.Date;

@Data
@ApiModel("上传游戏信息请求体")
public class GameInfoAdd {
    @Getter
    @ApiModelProperty("游戏名称")
    private final String name;

    @Getter
    @ApiModelProperty("游戏价格（单位为人民币元）")
    private final Integer price;

    @Getter
    @ApiModelProperty("上架日期")
    private final Date releaseDate;

    @Getter
    @ApiModelProperty("游戏介绍")
    private final String describe;

    @Getter
    @ApiModelProperty("支持的系统列表")
    private final Collection<GameInfo.SupportSystem> supportSystems;

    @Getter
    @ApiModelProperty("是否为多人联机游戏")
    private final boolean multiPlayer;

    @Getter
    @ApiModelProperty("最低适合玩该游戏的年龄，如为 0 则表示全年龄段")
    private final Integer minAge;
}
