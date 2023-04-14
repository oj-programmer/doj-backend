package com.doj.server.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2023/04/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;

    private Integer userId;

    private Integer entityType;

    private Integer entityId;

    private Integer targetId;

    private Integer status;

    private OffsetDateTime createTime;

    private String content;
}