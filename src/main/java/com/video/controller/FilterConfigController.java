package com.video.controller;

import com.video.dto.business.VideoFilterCfgDTO;
import com.video.dto.common.Page;
import com.video.dto.common.ResponseResult;
import com.video.service.FilterConfigService;
import com.video.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * description: 过滤项配置管理控制器
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 22:45
 */
@Api(tags = "过滤项配置管理")
@CrossOrigin
@RestController
@RequestMapping("/config")
public class FilterConfigController {

    @Resource
    private FilterConfigService cfgService;

    /**
     * 添加过滤项配置
     * @param cfgDTO 过滤项配置信息
     * @return 操作结果
     */
    @ApiOperation(value = "添加过滤项配置")
    @ApiImplicitParam(name = "cfgDTO", value = "过滤项配置信息", required = true, dataType = "VideoFilterCfgDTO", dataTypeClass = VideoFilterCfgDTO.class)
    @PostMapping("/add")
    public ResponseResult<Object> add(@RequestBody VideoFilterCfgDTO cfgDTO) {
        try {
            cfgService.add(cfgDTO);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 修改过滤项配置
     * @param cfgDTO 过滤项配置信息
     * @return 操作结果
     */
    @ApiOperation(value = "修改过滤项配置")
    @ApiImplicitParam(name = "cfgDTO", value = "过滤项配置信息", required = true, dataType = "VideoFilterCfgDTO", dataTypeClass = VideoFilterCfgDTO.class)
    @PostMapping("/update")
    public ResponseResult<Object> update(@RequestBody VideoFilterCfgDTO cfgDTO) {
        try {
            cfgService.update(cfgDTO);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 删除过滤项配置
     * @param id 过滤项配置id
     * @return 操作结果
     */
    @ApiOperation(value = "删除过滤项配置")
    @ApiImplicitParam(name = "id", value = "过滤项配置id", required = true, dataType = "String", dataTypeClass = String.class)
    @GetMapping("/delete/{id}")
    public ResponseResult<Object> delete(@PathVariable String id) {
        try {
            cfgService.delete(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 按条件查询过滤项配置
     * @param cfgDTO 过滤项配置
     * @return 查询结果
     */
    @ApiOperation(value = "按条件查询过滤项配置")
    @ApiImplicitParam(name = "cfgDTO", value = "过滤项配置", required = true, dataType = "VideoFilterCfgDTO", dataTypeClass = VideoFilterCfgDTO.class)
    @PostMapping("/list")
    public ResponseResult<Page<List<VideoFilterCfgDTO>>> list(@RequestBody VideoFilterCfgDTO cfgDTO) {
        try {
            // 统计数据总量
            int count = cfgService.count(cfgDTO);
            cfgDTO.setTotal(count);

            // 计算分页开始索引位置
            int startIdx = PageUtil.computeStartIdx(cfgDTO.getPage(), cfgDTO.getPageSize());
            cfgDTO.setStartIdx(startIdx);

            // 查询数据
            List<VideoFilterCfgDTO> result = cfgService.list(cfgDTO);
            Page<List<VideoFilterCfgDTO>> listPage = Page.pageInfo(cfgDTO.getPage(), cfgDTO.getPageSize(), count, result);
            return ResponseResult.success(listPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
    }

    /**
     * 查询下拉框中的过滤项配置
     * @param dto 过滤项配置查询数据
     * @return 操作结果
     */
    @ApiOperation(value = "查询下拉框中的过滤项配置")
    @ApiImplicitParam(name = "dto", value = "过滤项配置查询数据", required = true, dataType = "VideoFilterCfgDTO", dataTypeClass = VideoFilterCfgDTO.class)
    @PostMapping("/select-options")
    public ResponseResult<List<VideoFilterCfgDTO>> selectOptions(@RequestBody VideoFilterCfgDTO dto) {
        try {
            List<VideoFilterCfgDTO> resultList = cfgService.selectOptions(dto.getParentId(), dto.getKey(), dto.getType());
            return ResponseResult.success(resultList);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
    }
}
