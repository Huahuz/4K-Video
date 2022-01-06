package com.video.mapper;

import com.video.dto.business.VideoDTO;
import com.video.dto.business.VideoDetailDTO;
import com.video.dto.business.VideoResultDTO;
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
     * 根据提供的id查询视频的详细信息
     * @param id 查询的视频id
     * @return 操作结果
     */
    VideoDetailDTO detail(@Param("id") String id) throws SQLException;

    /**
     * 将详细的视频信息添加进数据库
     * @param dto 添加的视频信息
     * @return 操作结果
     */
    void add(@Param("dto") VideoDetailDTO dto) throws SQLException;

    /**
     * 将视频信息进行修改，同时修改对应的图片和链接信息
     * @param dto 添加的视频信息
     * @return 操作结果
     */
    void update(@Param("dto") VideoDetailDTO dto) throws SQLException;
}