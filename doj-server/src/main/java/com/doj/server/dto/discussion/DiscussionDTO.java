package com.doj.server.dto.discussion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 类描述: 讨论帖子 DTO
 *
 * @author kongweikun@163.com
 * @date 2023/4/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionDTO {

    private String discussionId;

    private String userId;

    private String description;

    private String content;

    private Integer likeNum;

    private Integer viewNum;

    private Integer commentNum;

    /**
     * 置顶优先级
     */
    private Integer topPriority;

    private Long createTime;

    private Long updateTime;
}
