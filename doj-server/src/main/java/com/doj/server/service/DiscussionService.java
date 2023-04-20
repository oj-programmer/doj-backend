package com.doj.server.service;

import com.doj.server.dto.discussion.DiscussionDTO;

/**
 * 类描述: 讨论帖子 service
 *
 * @author kongweikun@163.com
 * @date 2023/4/17
 */
public interface DiscussionService {

    /**
     * 添加帖子
     * @param discussionDTO
     * @return
     */
    String addDiscussion(DiscussionDTO discussionDTO);

    /**
     * 帖子详情
     * @param discussionId
     * @return
     */
    DiscussionDTO getDiscussion(String discussionId);

    /**
     * 更新帖子内容
     * @param discussionDTO
     */
    void updateDiscussion(DiscussionDTO discussionDTO);

    /**
     * 删除帖子
     * @param discussionId
     */
    void deleteDiscussion(String discussionId);

    /**
     * 置顶帖子
     * @param discussionId
     */
    void topDiscussion(String discussionId);

    /**
     * 加精帖子
     * @param discussionId
     */
    void wonderfulDiscussion(String discussionId);


}
