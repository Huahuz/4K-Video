package com.video.controller;

import com.video.dto.business.VideoPictureDTO;
import com.video.dto.common.Page;
import com.video.dto.common.ResponseResult;
import com.video.service.VideoPictureService;
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
 * description: 视频图片功能控制器
 *
 * @author fxx
 * @version 1.0
 * @since 2021/12/24 15:33
 */
@Api(tags = "视频图片功能模块")
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private VideoPictureService videoPictureService;

    /**
     * 视频图片信息的添加
     *
     * @param pictureDTO 图片信息
     * @return 操作结果
     */
    @ApiOperation(value = "添加视频图片的方法")
    @ApiImplicitParam(name = "pictureDTO", value = "视频图片信息", required = true)
    @PostMapping("/add")
    public ResponseResult<Object> add(@RequestBody VideoPictureDTO pictureDTO) {
        try {
            videoPictureService.add(pictureDTO);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 视频图片信息修改
     * @param id 图片id
     * @param pictureDTO 图片信息
     * @return 操作结果
     */
    @ApiOperation(value = "视频图片信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "视频图片id", required = true),
            @ApiImplicitParam(name = "pictureDTO", value = "视频图片信息", required = true)
    })
    @GetMapping("/{id}/update")
    public ResponseResult<Object> update(@RequestBody String id, @RequestBody VideoPictureDTO pictureDTO) {
        try {
            videoPictureService.update(id, pictureDTO);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }


    /**
     * 按条件查询图片信息
     * @param pictureDTO 筛选条件
     * @return 查询结果
     */
    @ApiOperation(value = "按条件查询图片信息")
    @ApiImplicitParam(name = "commentDTO", value = "筛选条件", required = true)
    @PostMapping("/list")
    public ResponseResult<Page<List<VideoPictureDTO>>> list(@RequestBody VideoPictureDTO pictureDTO) {
        try {
            // 统计数据总量
            int count = videoPictureService.count(pictureDTO);
            pictureDTO.setTotal(count);

            // 计算分页开始索引位置
            int startIdx = PageUtil.computeStartIdx(pictureDTO.getPage(), pictureDTO.getPageSize());
            pictureDTO.setStartIdx(startIdx);

            // 查询数据
            List<VideoPictureDTO> result = videoPictureService.list(pictureDTO);
            Page<List<VideoPictureDTO>> listPage = Page.pageInfo(pictureDTO.getPage(), pictureDTO.getPageSize(), count, result);
            return ResponseResult.success(listPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }

    }

    /**
     * 按id删除图片信息
     * @param id 要删除的图片id
     * @return 查询结果
     */
    @ApiOperation(value = "按id删除图片信息")
    @ApiImplicitParam(name = "id", value = "视频图片id", required = true)
    @PostMapping("/delete/{id}")
    public ResponseResult<Object> delete(@PathVariable String id){
        try {
            videoPictureService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }


    /**
     * 批量删除图片信息
     * @param ids 要删除的图片id列表
     * @return 查询结果
     */
    @ApiOperation(value = "按id删除图片信息")
    @ApiImplicitParam(name = "ids", value = "视频图片id", required = true)
    @PostMapping("/deleteBatch/{ids}")
    public ResponseResult<Object> deleteBatch(@PathVariable List<String> ids){
        try {
            videoPictureService.deleteBatch(ids);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }


}
