package com.doj.server.controller.user;

import com.doj.server.infrastructure.annotation.Log;
import com.doj.server.infrastructure.response.ResultModel;
import com.doj.server.infrastructure.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：用户Controller
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user/api/v1")
public class UserController {

    @Log(title = "用户信息")
    @GetMapping("infos")
    @ApiOperation(value = "用户信息列表", notes = "支持分页查找")
    public ResultModel<String> listUserInfos() {
        String name = "name";
        return ResultUtil.resultSuccess(name);
    }
}
