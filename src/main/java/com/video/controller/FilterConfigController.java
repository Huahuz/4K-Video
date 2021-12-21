package com.video.controller;

import com.video.dto.business.VideoFilterCfgDTO;
import com.video.dto.common.Page;
import com.video.dto.common.ResponseResult;
import com.video.service.FilterConfigService;
import com.video.util.PageUtil;
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
     * 修改过滤项配置
     * @param id 过滤项配置id
     * @return 操作结果
     */
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
    @PostMapping("/list")
    public ResponseResult<Page<List<VideoFilterCfgDTO>>> list(@RequestBody VideoFilterCfgDTO cfgDTO) {
        try {
            // 统计数据总量
            int count = cfgService.count(cfgDTO);
            cfgDTO.setTotal(count);

            // 计算分页开始索引位置
            int startIdx = PageUtil.computeStartIdx(cfgDTO.getPage(), cfgDTO.getSize());
            cfgDTO.setStartIdx(startIdx);

            // 查询数据
            List<VideoFilterCfgDTO> result = cfgService.list(cfgDTO);
            Page<List<VideoFilterCfgDTO>> listPage = Page.pageInfo(cfgDTO.getPage(), cfgDTO.getSize(), count, result);
            return ResponseResult.success(listPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseResult.failure();
        }
    }
}
