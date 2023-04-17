package com.doj.server.service.login;

import com.doj.server.dto.login.LoginDTO;
import com.doj.server.dto.login.RegisterDTO;
import com.doj.server.infrastructure.annotation.Log;
import com.doj.server.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * 类描述: 登陆 service 实现类
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Log(title = "登陆")
    @Override
    public void login(LoginDTO loginDTO) {

    }

    @Log(title = "注册")
    @Override
    public void register(RegisterDTO registerDTO) {

    }
}
