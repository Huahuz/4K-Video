package com.video.service;

import com.video.dto.business.VideoFilterCfgDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * description: 过滤项配置接口
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 22:54
 */
public interface FilterConfigService {
    /**
     * 添加过滤项配置信息
     * @param cfgDTO 过滤项配置信息
     * @throws SQLException sql异常
     */
    void add(VideoFilterCfgDTO cfgDTO) throws SQLException;

    /**
     * 修改过滤项配置信息
     * @param cfgDTO 过滤项配置信息
     * @throws SQLException sql异常
     */
    void update(VideoFilterCfgDTO cfgDTO) throws SQLException;

    /**
     * 删除过滤项配置信息
     * @param id 过滤项配置id
     * @throws SQLException sql异常
     */
    void delete(String id) throws SQLException;

    /**
     * 根据条件统计过滤项配置数量
     * @param cfgDTO 过滤项配置信息
     * @return 统计结果
     * @throws SQLException sql异常
     */
    int count(VideoFilterCfgDTO cfgDTO) throws SQLException;

    /**
     * 根据条件查询过滤项配置数量
     * @param cfgDTO 过滤项配置信息
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<VideoFilterCfgDTO> list(VideoFilterCfgDTO cfgDTO) throws SQLException;
}
