package com.video.mapper;

import com.video.dto.business.VideoDownloadLinkDTO;
import com.video.entity.VideoDownloadLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.sql.SQLException;
import java.util.List;

@Mapper
public interface VideoDownloadLinkMapper {
    /**
     * 添加视频下载链接
     * @param downloadLinkServiceDTO 评论信息
     * @throws SQLException sql执行异常
     */
    void add(@Param("dto") VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException;

    /**
     * 根据id修改视频下载链接
     * @param id 修改的id
     * @param status 修改后的状态
     * @throws SQLException sql异常
     */
    void switchStatus(@Param("id") String id,@Param("status") Integer status) throws SQLException;

    /**
     * 根据条件查询视频下载链接
     * @param downloadLinkServiceDTO 筛选条件
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<VideoDownloadLink> list(@Param("dto") VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException;

    /**
     * 根据条件查询统计视频下载链接数量
     * @param downloadLinkServiceDTO 筛选条件
     * @return 统计结果
     * @throws SQLException sql异常
     */
    int count(@Param("dto") VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException;

    /**
     * 删除视频下载链接
     * @param id 视频下载链接id
     * @throws SQLException sql异常
     */
    void delete(@Param("id") String id) throws SQLException;

    /**
     * 删除视频下载链接
     * @param ids 视频下载链接id列表
     * @throws SQLException sql异常
     */
    void deleteBatch(@Param("ids") String[] ids) throws SQLException;




}