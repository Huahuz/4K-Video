package com.video.controller;

import com.video.dto.business.VideoSysCfgDTO;
import com.video.dto.common.Page;
import com.video.dto.common.ResponseResult;
import com.video.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * description: 系统配置管理
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 22:45
 */
@Api(tags = "系统配置管理")
@CrossOrigin
@RestController
@RequestMapping("/sys-config")
public class SysConfigController {

    @Resource
    private SysConfigService cfgService;

    /**
     * 添加系统配置
     * @param cfgDTO 系统配置信息
     * @return 操作结果
     */
    @ApiOperation(value = "添加系统配置")
    @ApiImplicitParam(name = "cfgDTO", value = "系统配置信息", required = true)
    @PostMapping("/add")
    public ResponseResult<Object> add(@RequestBody VideoSysCfgDTO cfgDTO) {
        try {
            cfgService.add(cfgDTO);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 修改系统配置
     * @param cfgDTO 系统配置信息
     * @return 操作结果
     */
    @ApiOperation(value = "修改系统配置")
    @ApiImplicitParam(name = "cfgDTO", value = "系统配置信息", required = true)
    @PostMapping("/update")
    public ResponseResult<Object> update(@RequestBody VideoSysCfgDTO cfgDTO) {
        try {
            cfgService.update(cfgDTO);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
        return ResponseResult.success();
    }

    /**
     * 删除系统配置
     * @param id 系统配置id
     * @return 操作结果
     */
    @ApiOperation(value = "删除系统配置")
    @ApiImplicitParam(name = "id", value = "系统配置id", required = true)
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
     * 按条件查询系统配置
     * @return 查询结果
     */
    @ApiOperation(value = "按条件查询系统配置")
    @GetMapping("/list")
    public ResponseResult<Page<List<VideoSysCfgDTO>>> list() {
        try {
            // 查询数据
            List<VideoSysCfgDTO> result = cfgService.list();
            Page<List<VideoSysCfgDTO>> listPage = Page.pageInfo(0, 0, 0, result);
            return ResponseResult.success(listPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
    }
}
