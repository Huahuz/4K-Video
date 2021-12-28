package com.video.service;

import com.video.dto.business.VideoSysCfgDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * description: 系统配置接口
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 22:54
 */
public interface SysConfigService {
    /**
     * 添加系统配置信息
     * @param cfgDTO 系统配置信息
     * @throws SQLException sql异常
     */
    void add(VideoSysCfgDTO cfgDTO) throws SQLException;

    /**
     * 修改系统配置信息
     * @param cfgDTO 系统配置信息
     * @throws SQLException sql异常
     */
    void update(VideoSysCfgDTO cfgDTO) throws SQLException;

    /**
     * 删除系统配置信息
     * @param id 系统配置id
     * @throws SQLException sql异常
     */
    void delete(String id) throws SQLException;

    /**
     * 根据条件查询系统配置数量
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<VideoSysCfgDTO> list() throws SQLException;
}
