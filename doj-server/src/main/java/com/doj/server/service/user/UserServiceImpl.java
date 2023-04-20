package com.doj.server.service.user;

import com.doj.server.dto.user.LoginTicketDTO;
import com.doj.server.dto.user.UserDTO;
import com.doj.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 类描述: 用户服务 实现类
 *
 * @author kongweikun@163.com
 * @date 2023/4/18
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserDTO getUserByUserId(String userId) {
        return UserDTO.builder()
                .userId("user-11111111")
                .username("weikunkun")
                .build();
    }

    @Override
    public LoginTicketDTO getLoginTicket(String ticket) {
        return LoginTicketDTO.builder()
                .ticket("ticket")
                .userId("user-11111111")
                .expired(new Date(1713452598))
                .build();
    }
}
