package com.doj.server.service;

import com.doj.server.dto.login.LoginDTO;
import com.doj.server.dto.login.RegisterDTO;

/**
 * 类描述: 登陆 Service
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
public interface LoginService {

    /**
     * 登陆
     * @param loginDTO
     */
    void login(LoginDTO loginDTO);

    void register(RegisterDTO registerDTO);

}
