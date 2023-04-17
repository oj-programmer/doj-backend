package com.doj.server.service;

import com.doj.server.dto.discussion.DiscussionDTO;

/**
 * 类描述: 讨论帖子 service
 *
 * @author kongweikun@163.com
 * @date 2023/4/17
 */
public interface DiscussionService {

    String addDiscussion(DiscussionDTO discussionDTO);
}
