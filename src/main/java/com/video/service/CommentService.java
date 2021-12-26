package com.video.service;

import com.video.dto.business.VideoCommentDTO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * description: 评论服务接口类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 16:54
 */
public interface CommentService {
    /**
     * 新增评论
     * @param commentDTO 评论信息
     */
    void add(VideoCommentDTO commentDTO) throws SQLException;

    /**
     * 根据id删除评论
     * @param id 评论id
     */
    void delete(String id) throws SQLException;

    /**
     * 根据id删除批量评论
     * @param ids 评论id
     */
    void deleteBatch(String ids) throws SQLException;

    /**
     * 根据id修改评论审核状态
     * @param id 评论id
     * @param status 评论状态
     */
    void switchStatus(String id, Integer status) throws SQLException;

    /**
     * 根据条件查询评论列表
     * @param commentDTO 筛选条件
     * @return 查询结果
     */
    List<VideoCommentDTO> list(@Param("dto") VideoCommentDTO commentDTO) throws SQLException;

    /**
     * 根据条件查询统计评论数量
     * @param commentDTO 筛选条件
     * @return 统计结果
     */
    int count(VideoCommentDTO commentDTO) throws SQLException;

    /**
     * 根据ids批量修改评论审核状态
     * @param ids 评论id
     * @param status 评论状态
     */
    void switchStatusBatch(String ids, Integer status) throws SQLException;
}
