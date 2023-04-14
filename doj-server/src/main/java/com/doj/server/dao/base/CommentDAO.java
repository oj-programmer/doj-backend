package com.doj.server.dao.base;

import com.doj.server.model.Comment;

/**
* Created by Mybatis Generator on 2023/04/14
*/
public interface CommentDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}