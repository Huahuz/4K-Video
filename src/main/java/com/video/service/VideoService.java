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

    /**
     * 修改视频置顶状态
     * @param id 视频id
     * @param status 视频状态
     * @throws SQLException sql异常
     */
    void top(String id, String status) throws SQLException;

    /**
     * 批量视频审核状态修改
     * @param ids 视频id
     * @param status 审核状态
     * @throws SQLException sql异常
     */
    void switchStatusBatch(String ids, String status) throws SQLException;

    /**
     * 单条视频审核状态修改
     * @param id 视频id
     * @param status 审核状态
     * @throws SQLException sql异常
     */
    void switchStatus(String id, String status) throws SQLException;

    /**
     * 删除单条视频
     * @param id 视频id
     */
    void delete(String id);

    /**
     * 批量删除视频
     * @param ids 视频ids
     */
    void deleteBatch(String ids);
}
