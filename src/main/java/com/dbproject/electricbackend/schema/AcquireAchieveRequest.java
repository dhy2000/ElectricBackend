package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("获得游戏成就请求")
public class AcquireAchieveRequest {
    @Getter
    @ApiModelProperty("成就 ID")
    private int achievementId;
}
