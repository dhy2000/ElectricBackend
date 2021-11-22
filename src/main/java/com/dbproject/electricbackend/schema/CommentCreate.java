package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@ApiModel("创建评论请求")
public class CommentCreate {
    @Getter
    @NonNull
    @ApiModelProperty("标题")
    private String title;

    @Getter
    @NonNull
    @ApiModelProperty("内容")
    private String content;

    @Getter
    @NonNull
    @ApiModelProperty("所属游戏ID")
    private int gameId;

    @Getter
    @ApiModelProperty("回复指代的评论ID，为 null 表示新评论非回帖")
    private Integer reply;
}
