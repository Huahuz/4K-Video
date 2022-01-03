package com.video.mapper;

import com.video.dto.business.DeleteInfoDTO;
import com.video.dto.business.VideoDTO;
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
}