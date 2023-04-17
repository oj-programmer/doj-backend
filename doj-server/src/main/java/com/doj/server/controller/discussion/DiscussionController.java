package com.doj.server.controller.discussion;

import com.doj.server.dto.discussion.DiscussionDTO;
import com.doj.server.infrastructure.response.ResultModel;
import com.doj.server.infrastructure.utils.BeanUtil;
import com.doj.server.infrastructure.utils.ResultUtil;
import com.doj.server.service.DiscussionService;
import com.doj.server.vo.discussion.AddDiscussionReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/list")
    @ApiOperation(value = "讨论列表", notes = "支持分页查找")
    public ResultModel listDiscussions() {
        return ResultUtil.resultSuccess();
    }

    @PostMapping("/add")
    public ResultModel<String> addDiscussion(@RequestBody AddDiscussionReqVO addReqVO) {
        DiscussionDTO discussionDTO = BeanUtil.copyPropertiesIgnoreNull(addReqVO, DiscussionDTO::new);
        String discussionId = discussionService.addDiscussion(discussionDTO);
        return ResultUtil.resultSuccess(discussionId);
    }
}
