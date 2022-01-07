package com.video.mapper;

import com.video.dto.business.*;
import com.video.dto.business.app.Comment;
import com.video.dto.business.app.DownloadLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface VideoMapper {

    /**
     * 根据条件查询视频列表
     * @param videoDTO 查询条件
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<VideoResultDTO> list(@Param("dto") VideoDTO videoDTO) throws SQLException;

    /**
     * 根据条件统计条数
     * @param videoDTO 查询条件
     * @return 查询结果
     * @throws SQLException sql异常
     */
    int count(@Param("dto") VideoDTO videoDTO) throws SQLException;

    /**
     * 修改视频置顶状态
     * @param id 视频id
     * @param status 视频状态
     * @throws SQLException sql异常
     */
    void top(@Param("id") String id, @Param("status") String status) throws SQLException;

    /**
     * 批量视频审核状态修改
     * @param idArr 视频id
     * @param status 审核状态
     * @throws SQLException sql异常
     */
    void switchStatusBatch(@Param("idArr") String[] idArr, @Param("status") String status) throws SQLException;

    /**
     * 批量视频删除
     * @param idArr 视频id
     * @throws SQLException sql异常
     */
    void deleteBatch(@Param("idArr") String[] idArr) throws SQLException;

    /**
     * 批量查询视频关联信息
     * @param idArr 视频id
     * @return 删除信息
     * @throws SQLException sql异常
     */
    List<DeleteInfoDTO> getDeleteInfo(String[] idArr) throws SQLException;

    /**

     * 根据提供的id查询视频的详细信息
     * @param id 查询的视频id
     * @return 操作结果
     */
    VideoDetailDTO detail(@Param("id") String id) throws SQLException;

    /**
     * 将详细的视频信息添加进数据库
     * @param dto 添加的视频信息
     */
    void add(@Param("dto") VideoDetailDTO dto) throws SQLException;

    /**
     * 将视频信息进行修改，同时修改对应的图片和链接信息
     * @param dto 添加的视频信息
     */
    void update(@Param("dto") VideoDetailDTO dto) throws SQLException;

    /**
     * 根据条件查询视频列表
     * @param queryParam 小程序查询条件
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<AppQueryResult> searchAppList(@Param("dto") AppQueryParam queryParam) throws SQLException;

    /**
     * 根据id查询小程序展示视频详情
     * @param id id
     * @return 查询结果
     */
    AppDetailInfo getAppDetail(@Param("id") String id);

    /**
     * 根据ID查询评论
     * @param id 视频id
     * @return 查询结果
     */
    List<Comment> getCommentById(@Param("id") String id);

    /**
     * 根据ID查询下载链接
     * @param id 视频id
     * @return 查询结果
     */
    List<DownloadLink> getLinkById(@Param("id") String id);
}