package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@ApiModel("支付订单请求")
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderRequest {
    @Getter
    @ApiModelProperty("支付的订单号")
    private Integer orderId;
}
