package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Collection;
import java.util.Date;

@Data
@ApiModel("游戏介绍信息")
@AllArgsConstructor
public class GameInfo {
    @Getter
    @ApiModelProperty("游戏编号（不展示，前端需要保存以进行后续可能的请求）")
    private final Integer id;

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
    @ApiModelProperty("是否为多人联机游戏")
    private final boolean multiPlayer;

    @Data
    @ApiModel("游戏支持的设备列表")
    @AllArgsConstructor
    public static class SupportDevices {
        @Data
        @ApiModel("游戏支持的一种系统的具体信息")
        @AllArgsConstructor
        public static class System {
            @Getter
            @ApiModelProperty("操作系统名称")
            private final String name;

            @Getter
            @ApiModelProperty("最低所需版本")
            private final String version;

            @Getter
            @ApiModelProperty("最低需要的处理器配置")
            private final String processor;

            @Getter
            @ApiModelProperty("最低需要的内存配置")
            private final String memory;
        }

        @Getter
        @ApiModelProperty("支持的系统列表")
        private final Collection<System> system;
    }

    @Getter
    @ApiModelProperty("支持的系统")
    private final SupportDevices supportDevices;

    @Getter
    @ApiModelProperty("最低适合玩该游戏的年龄，如为 0 则表示全年龄段")
    private final Integer minAge;
}
