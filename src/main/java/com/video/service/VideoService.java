package com.video.service;

import com.video.dto.business.*;

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

    /**
     * 根据提供的id查询视频的详细信息
     * @param id 查询的视频id
     * @return 操作结果
     */
    VideoDetailDTO detail(String id) throws SQLException;

    /**
     * 将详细的视频信息添加进数据库
     * @param dto 添加的视频信息
     */
    void add(VideoDetailDTO dto) throws SQLException;

    /**
     * 将视频信息进行修改，同时修改对应的图片和链接信息
     * @param dto 添加的视频信息
     */
    void update(VideoDetailDTO dto) throws SQLException;

    /**
     * 查询置顶视频信息
     * @return 查询结果
     */
    List<AppQueryResult> topList();

    /**
     * 查询首页视频信息
     * @param param 查询参数
     * @return 查询结果
     */
    List<AppQueryResult> appList(AppQueryParam param);

    /**
     * 查询视频详情
     * @param id 视频id
     * @return 视频详情
     */
    AppDetailInfo appDetail(String id);

    /**
     * 前台页面个人资源添加
     * @param appAddInfo 视频资源信息
     * @return 视频详情
     */
    void AppAdd(AppAddInfo appAddInfo) throws SQLException;

    /**
     * 数据迁移
     */
    void data();
}
