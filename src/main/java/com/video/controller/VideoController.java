package com.video.controller;

import com.video.dto.business.VideoDTO;
import com.video.dto.business.VideoResultDTO;
import com.video.dto.common.Page;
import com.video.dto.common.ResponseResult;
import com.video.service.VideoService;
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
 * description: 视频管理控制层
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/1 22:14
 */
@Api(tags = "视频管理控制层")
@CrossOrigin
@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    /**
     * 视频列表查询
     * @param videoDTO 查询条件
     * @return 查询结果
     */
    @ApiOperation("视频列表查询")
    @ApiImplicitParam(name = "videoDTO", value = "查询条件", required = true, dataType = "VideoDTO", dataTypeClass = VideoDTO.class)
    @PostMapping("/list")
    public ResponseResult<Page<List<VideoResultDTO>>> list(@RequestBody VideoDTO videoDTO) {
        try {
            // 统计数据总量
            int count = videoService.count(videoDTO);
            videoDTO.setTotal(count);

            // 计算分页开始索引位置
            int startIdx = PageUtil.computeStartIdx(videoDTO.getPage(), videoDTO.getPageSize());
            videoDTO.setStartIdx(startIdx);

            // 查询数据
            List<VideoResultDTO> result = videoService.list(videoDTO);
            Page<List<VideoResultDTO>> listPage = Page.pageInfo(videoDTO.getPage(), videoDTO.getPageSize(), count, result);
            return ResponseResult.success(listPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
    }

    /**
     * 修改视频置顶状态
     * @param id 视频id
     * @param status 视频状态
     * @return 操作结果
     */
    @ApiOperation("修改视频置顶状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "视频id", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "status", value = "置顶状态", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/{id}/top/{status}")
    public ResponseResult<Object> top(@PathVariable("id") String id, @PathVariable("status") String status) {
        try {
            videoService.top(id, status);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 批量视频审核状态修改
     * @param ids 视频id
     * @param status 审核状态
     * @return 操作结果
     */
    @ApiOperation("批量视频审核状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "视频id", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "status", value = "审核状态", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/{ids}/switch-status-batch/{status}")
    public ResponseResult<Object> switchStatusBatch(@PathVariable("ids") String ids, @PathVariable("status") String status) {
        try {
            videoService.switchStatusBatch(ids, status);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 单条视频审核状态修改
     * @param id 视频id
     * @param status 审核状态
     * @return 操作结果
     */
    @ApiOperation("单条视频审核状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "视频id", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "status", value = "审核状态", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/{id}/switch-status/{status}")
    public ResponseResult<Object> switchStatus(@PathVariable("id") String id, @PathVariable("status") String status) {
        try {
            videoService.switchStatus(id, status);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 删除单条视频信息
     * @param id 视频id
     * @return 操作结果
     */
    @ApiOperation("删除单条视频信息")
    @ApiImplicitParam(name = "id", value = "视频id", required = true, dataType = "String", dataTypeClass = String.class)
    @GetMapping("/delete/{id}")
    public ResponseResult<Object> delete(@PathVariable("id") String id) {
        try {
            videoService.delete(id);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 批量删除视频信息
     * @param ids 视频id
     * @return 操作结果
     */
    @ApiOperation("批量删除视频信息")
    @ApiImplicitParam(name = "ids", value = "视频id", required = true, dataType = "String", dataTypeClass = String.class)
    @GetMapping("/delete-batch/{ids}")
    public ResponseResult<Object> deleteBatch(@PathVariable("ids") String ids) {
        try {
            videoService.deleteBatch(ids);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }
}
