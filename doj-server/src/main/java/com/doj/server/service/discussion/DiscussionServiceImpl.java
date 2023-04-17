package com.doj.server.service.discussion;

import com.doj.server.dao.base.DiscussionDAO;
import com.doj.server.dto.discussion.DiscussionDTO;
import com.doj.server.infrastructure.annotation.Log;
import com.doj.server.infrastructure.enums.StatusEnum;
import com.doj.server.infrastructure.utils.BeanUtil;
import com.doj.server.infrastructure.utils.DateTimeUtil;
import com.doj.server.infrastructure.utils.ShortIDUtil;
import com.doj.server.model.discussion.DiscussionDO;
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
        long currentTime = DateTimeUtil.currentEpochMilli();
        String shortID = ShortIDUtil.generateDiscussionShortID();
        DiscussionDO discussionDO = BeanUtil.copyPropertiesIgnoreNull(discussionDTO, DiscussionDO::new,
                (source, target) -> {
                    target.setStatus(StatusEnum.NORMAL.getValue());
                    target.setDiscussionId(shortID);
                    target.setCreateTime(currentTime);
                    target.setUpdateTime(currentTime);
                });
        discussionDAO.insertSelective(discussionDO);
        return null;
    }
}
