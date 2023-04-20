package com.doj.server.vo.discussion;

import com.doj.server.infrastructure.config.validated.Add;
import com.doj.server.infrastructure.config.validated.Update;
import com.doj.server.infrastructure.config.validated.discusion.Top;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 类描述: 添加讨论帖子请求 VO

 * @author kongweikun@163.com
 * @date 2023/4/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscussionReqVO {


    @NotNull(groups = {Update.class, Top.class}, message = "帖子 id 不能为空")
    private String discussionId;

    @Size(max = 255, message = "概述内容不能超过255")
    private String description;

    private String content;


}
