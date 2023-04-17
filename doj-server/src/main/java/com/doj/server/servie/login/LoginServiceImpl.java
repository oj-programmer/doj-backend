package com.doj.server.servie.login;

import com.doj.server.dto.login.LoginDTO;
import com.doj.server.infrastructure.annotation.Log;
import com.doj.server.servie.LoginService;

/**
 * 类描述: TODO
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
public class LoginServiceImpl implements LoginService {

    @Log(title = "登陆")
    @Override
    public void login(LoginDTO loginDTO) {

    }
}
