package com.video.service;

import com.video.dto.business.VideoDTO;
import com.video.dto.business.VideoResultDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * description: 视频服务类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/2 16:00
 */
public interface VideoService {

    /**
     * 根据条件查询视频列表
     * @param videoDTO 查询条件
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<VideoResultDTO> list(VideoDTO videoDTO) throws SQLException;

    /**
     * 根据条件统计条数
     * @param videoDTO 查询条件
     * @return 查询结果
     * @throws SQLException sql异常
     */
    int count(VideoDTO videoDTO) throws SQLException;
}
