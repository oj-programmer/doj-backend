package com.doj.server.service;

import com.doj.server.dto.user.LoginTicketDTO;
import com.doj.server.dto.user.UserDTO;

/**
 * 类描述: 用户服务
 *
 * @author kongweikun@163.com
 * @date 2023/4/18
 */
public interface UserService {

    /**
     * 根据用户 id 获取用户信息
     * @param userId
     * @return
     */
    UserDTO getUserByUserId(String userId);

    /**
     * 根据 ticket 获取登陆信息
     * @param ticket
     */
    LoginTicketDTO getLoginTicket(String ticket);
}
