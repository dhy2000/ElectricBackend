package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("充值请求")
public class RechargeRequest {
    @Getter
    @ApiModelProperty("充值金额")
    private int amount;
}
