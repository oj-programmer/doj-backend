package com.doj.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2023/04/18
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discussion {
    private Long id;

    private String discussionId;

    private String description;

    private Integer viewNum;

    private Integer likeNum;

    private Integer topPriority;

    private Integer commentNum;

    private Integer status;

    private String createUser;

    private String updateUser;

    private Long createTime;

    private Long updateTime;

    private Long deleteTime;

    private String content;
}