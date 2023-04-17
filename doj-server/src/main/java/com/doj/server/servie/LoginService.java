package com.doj.server.servie;

import com.doj.server.dto.login.LoginDTO;

/**
 * 类描述: 登陆 Service
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
public interface LoginService {

    void login(LoginDTO loginDTO);
}
