package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;

@Data
@ApiModel("用户购买游戏的订单")
@AllArgsConstructor
public class PurchaseGameOrder {
    @Getter
    @ApiModelProperty("订单号")
    private final Integer id;

    @Getter
    @ApiModelProperty("购买的游戏编号")
    private final Integer gameId;

    @Getter
    @ApiModelProperty("购买者编号")
    private final Integer buyerId;

    @Getter
    @ApiModelProperty("买给的人的编号(可以是自己也可以是别人)")
    private final Integer receiverId;

    @Getter
    @ApiModelProperty("订单创建日期")
    private final Date createDate;

    @Getter
    @ApiModelProperty("是否已付款")
    private final boolean paid;

    @Getter
    @ApiModelProperty("付款日期")
    private final Date payDate;
}
