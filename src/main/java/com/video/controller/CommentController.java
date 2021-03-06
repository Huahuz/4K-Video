package com.video.controller;

import com.video.dto.business.VideoCommentDTO;
import com.video.dto.common.Page;
import com.video.dto.common.ResponseResult;
import com.video.service.CommentService;
import com.video.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * description: 评论功能控制器
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 15:33
 */
@Api(tags = "评论功能模块")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 添加评论方法
     * @param commentDTO 评论信息
     * @return 操作结果
     */
    @ApiOperation(value = "添加评论方法")
    @ApiImplicitParam(name = "commentDTO", value = "评论信息", required = true)
    @PostMapping("/add")
    public ResponseResult<Object> add(@RequestBody VideoCommentDTO commentDTO) {
        try {
            commentService.add(commentDTO);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 删除评论方法
     * @param id 评论id
     * @return 操作结果
     */
    @ApiOperation(value = "删除评论方法")
    @ApiImplicitParam(name = "id", value = "评论id", required = true)
    @GetMapping("/delete/{id}")
    public ResponseResult<Object> delete(@PathVariable String id) {
        try {
            commentService.delete(id);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 评论状态审核修改
     * @param id 评论id
     * @param status 评论状态
     * @return 操作结果
     */
    @ApiOperation(value = "评论状态审核修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论id", required = true),
            @ApiImplicitParam(name = "status", value = "评论状态", required = true)
    })
    @GetMapping("/{id}/switch-status/{status}")
    public ResponseResult<Object> switchStatus(@PathVariable String id, @PathVariable Integer status) {
        try {
            commentService.switchStatus(id, status);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 按条件查询评论
     * @param commentDTO 筛选条件
     * @return 查询结果
     */
    @ApiOperation(value = "按条件查询评论")
    @ApiImplicitParam(name = "commentDTO", value = "筛选条件", required = true)
    @PostMapping("/list")
    public ResponseResult<Page<List<VideoCommentDTO>>> list(@RequestBody VideoCommentDTO commentDTO) {
        try {
            // 统计数据总量
            int count = commentService.count(commentDTO);
            commentDTO.setTotal(count);

            // 计算分页开始索引位置
            int startIdx = PageUtil.computeStartIdx(commentDTO.getPage(), commentDTO.getSize());
            commentDTO.setStartIdx(startIdx);

            // 查询数据
            List<VideoCommentDTO> result = commentService.list(commentDTO);
            Page<List<VideoCommentDTO>> listPage = Page.pageInfo(commentDTO.getPage(), commentDTO.getSize(), count, result);
            return ResponseResult.success(listPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
    }
}
