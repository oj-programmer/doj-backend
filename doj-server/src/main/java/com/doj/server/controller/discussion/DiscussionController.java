package com.doj.server.controller.discussion;

import com.doj.server.infrastructure.response.ResultModel;
import com.doj.server.infrastructure.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：讨论 Controller
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Slf4j
@Api(tags = "讨论管理")
@RestController
@RequestMapping("/discussion/api/v1")
public class DiscussionController {

    @GetMapping("/discussions")
    @ApiOperation(value = "讨论列表", notes = "支持分页查找")
    public ResultModel listDiscussions() {
        log.info("讨论列表查找");
        return ResultUtil.resultSuccess();
    }
}
