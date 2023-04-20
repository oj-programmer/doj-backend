package com.doj.server.service.discussion;

import com.doj.server.dao.base.DiscussionDAO;
import com.doj.server.dto.discussion.DiscussionDTO;
import com.doj.server.dto.user.UserDTO;
import com.doj.server.infrastructure.annotation.Log;
import com.doj.server.infrastructure.enums.StatusEnum;
import com.doj.server.infrastructure.utils.BeanUtil;
import com.doj.server.infrastructure.utils.DateTimeUtil;
import com.doj.server.infrastructure.utils.ShortIDUtil;
import com.doj.server.infrastructure.utils.ThreadUtil;
import com.doj.server.model.Discussion;
import com.doj.server.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述: 讨论帖子 service
 *
 * @author kongweikun@163.com
 * @date 2023/4/17
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {


    @Autowired
    private DiscussionDAO discussionDAO;

    @Log(title = "添加讨论帖")
    @Override
    public String addDiscussion(DiscussionDTO discussionDTO) {
        UserDTO user = ThreadUtil.getUser();
        long currentTime = DateTimeUtil.currentEpochMilli();
        String shortID = ShortIDUtil.generateDiscussionShortID();
        Discussion discussionDO = BeanUtil.copyPropertiesIgnoreNull(discussionDTO, Discussion::new,
                (source, target) -> {
                    target.setStatus(StatusEnum.NORMAL.getValue());
                    target.setCreateUser(user.getUserId());
                    target.setDiscussionId(shortID);
                    target.setCreateTime(currentTime);
                    target.setUpdateTime(currentTime);
                });
        discussionDAO.insertSelective(discussionDO);
        return shortID;
    }

    /**
     * 详情 + 评论信息
     * @param discussionId
     * @return
     */
    @Log(title = "帖子详情")
    @Override
    public DiscussionDTO getDiscussion(String discussionId) {
        return null;
    }

    @Log(title = "更新帖子")
    @Override
    public void updateDiscussion(DiscussionDTO discussionDTO) {

    }

    @Log(title = "删除帖子")
    @Override
    public void deleteDiscussion(String discussionId) {

    }

    @Log(title = "置顶帖子")
    @Override
    public void topDiscussion(String discussionId) {

    }

    @Log(title = "帖子设置为精华")
    @Override
    public void wonderfulDiscussion(String discussionId) {

    }
}
