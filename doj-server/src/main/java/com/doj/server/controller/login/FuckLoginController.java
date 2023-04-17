package com.doj.server.controller.login;

import com.doj.server.infrastructure.response.ResultModel;
import com.doj.server.infrastructure.utils.ResultUtil;
import com.doj.server.vo.base.LoginInfoReqVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述: 登陆 controller
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
@RestController
@RequestMapping("/base/api/v1")
public class FuckLoginController {

    @PostMapping("/login")
    public ResultModel<Boolean> login(@RequestBody LoginInfoReqVO loginInfoReq) {


        return ResultUtil.resultSuccess();
    }
}
