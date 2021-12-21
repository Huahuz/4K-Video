package com.video.mapper;

import com.video.dto.business.VideoCommentDTO;
import com.video.entity.VideoComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface VideoCommentMapper {

    /**
     * 添加评论
     * @param commentDTO 评论信息
     * @throws SQLException sql执行异常
     */
    void add(@Param("dto") VideoCommentDTO commentDTO) throws SQLException;

    /**
     * 删除评论
     * @param id 评论id
     * @throws SQLException sql异常
     */
    void delete(@Param("id") String id) throws SQLException;

    /**
     * 根据id修改评论审核状态
     * @param id 评论id
     * @param status 评论状态
     * @throws SQLException sql异常
     */
    void switchStatus(@Param("id") String id, @Param("status") Integer status) throws SQLException;

    /**
     * 根据条件查询评论列表
     * @param commentDTO 筛选条件
     * @return 查询结果
     */
    List<VideoComment> list(@Param("dto") VideoCommentDTO commentDTO);

    /**
     * 根据条件查询统计评论数量
     * @param commentDTO 筛选条件
     * @return 统计结果
     */
    int count(@Param("dto") VideoCommentDTO commentDTO);
}