package com.doj.server.controller.discussion;

import com.doj.server.dto.discussion.DiscussionDTO;
import com.doj.server.infrastructure.config.validated.Add;
import com.doj.server.infrastructure.config.validated.Update;
import com.doj.server.infrastructure.config.validated.discusion.Top;
import com.doj.server.infrastructure.response.ResultModel;
import com.doj.server.infrastructure.utils.BeanUtil;
import com.doj.server.infrastructure.utils.ResultUtil;
import com.doj.server.service.DiscussionService;
import com.doj.server.vo.discussion.DiscussionReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/api/v1/discussions")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/list")
    @ApiOperation(value = "讨论列表", notes = "支持分页查找")
    public ResultModel listDiscussions() {
        return ResultUtil.resultSuccess();
    }

    @PostMapping("")
    @ApiOperation(value = "新增帖子", notes = "新增帖子")
    public ResultModel<String> addDiscussion(@RequestBody @Validated({Add.class}) DiscussionReqVO addReqVO) {
        DiscussionDTO discussionDTO = BeanUtil.copyPropertiesIgnoreNull(addReqVO, DiscussionDTO::new);
        String discussionId = discussionService.addDiscussion(discussionDTO);
        return ResultUtil.resultSuccess(discussionId);
    }

    @PutMapping("")
    @ApiOperation(value = "编辑帖子", notes = "编辑帖子")
    public ResultModel<Boolean> updateDiscussion(@RequestBody @Validated({Update.class}) DiscussionReqVO updateReqVO) {
        DiscussionDTO discussionDTO = BeanUtil.copyPropertiesIgnoreNull(updateReqVO, DiscussionDTO::new);
        discussionService.updateDiscussion(discussionDTO);
        return ResultUtil.resultSuccess();
    }

    @DeleteMapping("/{discussionId}")
    @ApiOperation(value = "删除帖子", notes = "删除帖子")
    public ResultModel<Boolean> deleteDiscussion(@PathVariable String discussionId) {
        discussionService.deleteDiscussion(discussionId);
        return ResultUtil.resultSuccess();
    }

    @PostMapping("/top")
    @ApiOperation(value = "置顶帖子", notes = "置顶帖子")
    public ResultModel<Boolean> topDiscussion(@RequestBody @Validated({Top.class}) DiscussionReqVO topReqVO) {
        discussionService.deleteDiscussion(topReqVO.getDiscussionId());
        return ResultUtil.resultSuccess();
    }


    @ PostMapping("/wonderful")
    @ApiOperation(value = "精华帖子", notes = "精华帖子")
    public ResultModel<Boolean> wonderfulDiscussion(@RequestBody @Validated({Top.class}) DiscussionReqVO reqVO) {
        discussionService.deleteDiscussion(reqVO.getDiscussionId());
        return ResultUtil.resultSuccess();
    }
}
