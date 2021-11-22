package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.sql.Date;

@Data
@ApiModel("评论")
@AllArgsConstructor
public class Comment {
    @Getter
    @ApiModelProperty("评论ID")
    private int id;

    @Getter
    @NonNull
    @ApiModelProperty("发布者ID")
    private int authorId;

    @Getter
    @NonNull
    @ApiModelProperty("发布者昵称")
    private String authorNickname;

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
    @ApiModelProperty("发布时间")
    private Date createTime;

    @Getter
    @ApiModelProperty("回复指代的评论编号, 可以为 null，null 表示该帖是新帖")
    private Integer replyId;
}
