package com.video.controller;

import com.video.dto.business.VideoDTO;
import com.video.dto.business.VideoFilterCfgDTO;
import com.video.dto.business.VideoResultDTO;
import com.video.dto.common.Page;
import com.video.dto.common.ResponseResult;
import com.video.service.VideoService;
import com.video.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @ApiImplicitParam(name = "videoDTO", value = "查询条件", required = true)
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
}
