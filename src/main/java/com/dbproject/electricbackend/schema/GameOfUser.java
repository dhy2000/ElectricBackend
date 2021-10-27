package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@ApiModel("描述用户拥有的游戏")
@AllArgsConstructor
@NoArgsConstructor
public class GameOfUser {
    @Getter
    @Setter
    @ApiModelProperty("游戏 id")
    private Integer id;

    @Getter
    @Setter
    @ApiModelProperty("游戏名称")
    private String name;

    @Getter
    @Setter
    @ApiModelProperty("所获成就")
    private List<GameAchievement> achievements;
}
