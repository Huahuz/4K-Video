package com.video.controller;

import com.video.dto.business.*;
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

    /**
     * 单条视频详情信息查询
     * @param id 视频id
     * @return 查询结果
     */
    @ApiOperation("单条视频详情信息查询")
    @ApiImplicitParam(name = "id", value = "视频id", required = true, dataType = "String", dataTypeClass = String.class)
    @GetMapping("/{id}/detail")
    public ResponseResult<VideoDetailDTO> detail(@PathVariable String id){
        try {
            VideoDetailDTO detailDTO = videoService.detail(id);
            return ResponseResult.success(detailDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseResult.failure();
        }
    }

    /**
     * 视频新增
     * @param videoDetailDTO 视频信息
     * @return 操作结果
     */
    @ApiOperation("视频新增")
    @ApiImplicitParam(name = "videoDetailDTO", value = "新增信息", required = true, dataType = "VideoDetailDTO", dataTypeClass = VideoDetailDTO.class)
    @PostMapping("/add")
    public ResponseResult<Object> add(@RequestBody VideoDetailDTO videoDetailDTO){

        try {
            videoDetailDTO.setStatus(0);
            videoService.add(videoDetailDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 视频修改
     * @param videoDetailDTO 视频信息
     * @return 操作结果
     */
    @ApiOperation("视频信息修改")
    @ApiImplicitParam(name = "videoDetailDTO", value = "修改信息", required = true, dataType = "VideoDetailDTO", dataTypeClass = VideoDetailDTO.class)
    @PostMapping("/update")
    public ResponseResult<Object> update(@RequestBody VideoDetailDTO videoDetailDTO) {

        try {
            videoService.update(videoDetailDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 小程序端置顶信息查询
     * @return 查询结果
     */
    @ApiOperation("小程序端置顶信息查询")
    @GetMapping("/top-list")
    public List<AppQueryResult> topList() {
        return videoService.topList();
    }

    /**
     * 小程序端查询列表
     * @param page 页码
     * @return 操作结果
     */
    @ApiOperation("小程序端查询列表")
    @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    @GetMapping("/{page}")
    public List<AppQueryResult> appIndexList(@PathVariable("page") Integer page) {
        AppQueryParam param = new AppQueryParam();
        param.setPage(page);
        param.setIsTop(0);
        return videoService.appList(param);
    }

    /**
     * 小程序端查询列表
     * @param param 查询条件
     * @return 操作结果
     */
    @ApiOperation("小程序端查询列表")
    @ApiImplicitParam(name = "param", value = "查询条件", required = true, dataType = "AppQueryParam", dataTypeClass = AppQueryParam.class)
    @PostMapping("/search")
    public List<AppQueryResult> appSearchList(@RequestBody AppQueryParam param) {
        param.setIsTop(1);
        return videoService.appList(param);
    }

    /**
     * 查询视频详情
     * @param id 视频id
     * @return 视频详情
     */
    @ApiOperation("查询视频详情")
    @ApiImplicitParam(name = "id", value = "视频id", required = true, dataType = "String", dataTypeClass = String.class)
    @GetMapping("/app/{id}/detail")
    public AppDetailInfo appDetail(@PathVariable("id") String id) {
        return videoService.appDetail(id);
    }

    /**
     * 前台页面个人资源添加
     * @param appAddInfo 视频资源信息
     * @return 视频详情
     */
    @ApiOperation("前台页面个人资源添加")
    @ApiImplicitParam(name = "appAddInfo", value = " 要添加的视频资源", required = true, dataType = "AppAddInfo", dataTypeClass = AppAddInfo.class)
    @PostMapping("/app/add")
    public ResponseResult<Object> appAdd(@RequestBody AppAddInfo appAddInfo) {
        try {
            videoService.AppAdd(appAddInfo);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 仅供数据迁移用的接口，上线后注释掉
     */
    @ApiOperation("数据库迁移操作")
    @GetMapping("/data")
    public void data() {
        videoService.data();
    }

    @GetMapping("/test")
    public List<VideoResultDTO> test() {
        return this.list(new VideoDTO()).getData().getData();
    }
}
