package com.video.controller;

import com.video.dto.business.VideoDownloadLinkDTO;
import com.video.dto.common.Page;
import com.video.dto.common.ResponseResult;
import com.video.service.DownloadLinkService;
import com.video.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * description: 下载链接功能控制器
 *
 * @author fxx
 * @version 1.0
 * @since 2021/12/31 16:41
 */
@Api(tags = "下载链接模块")
@CrossOrigin
@RestController
@RequestMapping("/download-link")
public class DownloadLinkController {
    @Resource
    private DownloadLinkService downloadLinkService;

    /**
     * 下载链接的添加
     * @param downloadLinkDTO 下载链接信息
     * @return 操作结果
     */
    @ApiOperation(value = "添加下载链接的方法")
    @ApiImplicitParam(name = "downloadLinkDTO", value = "下载链接信息", required = true)
    @PostMapping("/add")
    public ResponseResult<Object> add(@RequestBody VideoDownloadLinkDTO downloadLinkDTO) {
        try {
            downloadLinkService.add(downloadLinkDTO);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 下载链接信息修改
     * @param linkDTO 修改信息
     * @return 操作结果
     */
    @ApiOperation(value = "下载链接信息修改")
    @ApiImplicitParam(name = "linkDTO", value = "下载链接修改信息", required = true)
    @PostMapping("/update")
    public ResponseResult<Object> update(@RequestBody VideoDownloadLinkDTO linkDTO) {
        try {
            downloadLinkService.update(linkDTO);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }


    /**
     * 按条件查询下载链接
     * @param downloadLinkDTO 筛选条件
     * @return 查询结果
     */
    @ApiOperation(value = "按条件查询下载链接信息")
    @ApiImplicitParam(name = "downloadLinkDTO", value = "筛选条件", required = true)
    @PostMapping("/list")
    public ResponseResult<Page<List<VideoDownloadLinkDTO>>> list(@RequestBody VideoDownloadLinkDTO downloadLinkDTO) {
        try {
            // 统计数据总量
            int count = downloadLinkService.count(downloadLinkDTO);
            downloadLinkDTO.setTotal(count);
            // 计算分页开始索引位置
            int startIdx = PageUtil.computeStartIdx(downloadLinkDTO.getPage(), downloadLinkDTO.getPageSize());
            downloadLinkDTO.setStartIdx(startIdx);
            // 查询数据
            List<VideoDownloadLinkDTO> result = downloadLinkService.list(downloadLinkDTO);
            Page<List<VideoDownloadLinkDTO>> listPage = Page.pageInfo(downloadLinkDTO.getPage(), downloadLinkDTO.getPageSize(), count, result);
            return ResponseResult.success(listPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }

    }
    /**
     * 按id删除下载链接信息
     * @param id 要删除的下载链接id
     * @return 查询结果
     */
    @ApiOperation(value = "按id删除下载链接信息")
    @ApiImplicitParam(name = "id", value = "下载链接id", required = true)
    @GetMapping("/delete/{id}")
    public ResponseResult<Object> delete(@PathVariable String id){
        try {
            downloadLinkService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }


    /**
     * 批量删除下载链接信息
     * @param ids 要删除的下载链接信息id列表
     * @return 查询结果
     */
    @ApiOperation(value = "批量删除下载链接信息")
    @ApiImplicitParam(name = "ids", value = "下载链接信息id", required = true)
    @GetMapping("/delete-batch/{ids}")
    public ResponseResult<Object> deleteBatch(@PathVariable String ids){
        try {
            downloadLinkService.deleteBatch(ids);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }



}
