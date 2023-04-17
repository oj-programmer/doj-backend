package com.doj.server.dao.base;

import com.doj.server.model.discussion.DiscussionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
* Created by Mybatis Generator on 2023/04/17
*/
@Mapper
@Component
public interface DiscussionDAO {
    int deleteByPrimaryKey(Long id);

    int insert(DiscussionDO record);

    int insertSelective(DiscussionDO record);

    DiscussionDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DiscussionDO record);

    int updateByPrimaryKey(DiscussionDO record);
}