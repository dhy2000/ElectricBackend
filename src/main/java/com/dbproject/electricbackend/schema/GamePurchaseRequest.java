package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("购买游戏或创建订单请求")
@AllArgsConstructor
public class GamePurchaseRequest {
    @Getter
    @ApiModelProperty("购买者 id")
    private final Integer buyerId;

    @Getter
    @ApiModelProperty("买给的人的 id")
    private final Integer receiverId;

    @Getter
    @ApiModelProperty("要购买的游戏 id")
    private final Integer gameId;

    @Getter
    @ApiModelProperty("是否立即购买，true 则购买，false 则创建一个未支付的订单")
    private final boolean payNow;
}
