package com.doj.server.dao.base;

import com.doj.server.model.Discussion;
import org.apache.ibatis.annotations.Mapper;

/**
* Created by Mybatis Generator on 2023/04/18
*/
@Mapper
public interface DiscussionDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Discussion record);

    int insertSelective(Discussion record);

    Discussion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Discussion record);

    int updateByPrimaryKeyWithBLOBs(Discussion record);

    int updateByPrimaryKey(Discussion record);
}