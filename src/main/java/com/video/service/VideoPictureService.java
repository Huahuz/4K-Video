package com.video.service;

import com.video.dto.business.VideoCommentDTO;
import com.video.dto.business.VideoPictureDTO;
import com.video.entity.VideoPicture;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface VideoPictureService {
    /**
     * 新增视频图片
     * @param pictureDTO 视频图片信息
     */

    void add(VideoPictureDTO pictureDTO) throws SQLException;

    /**
     * 根据id修改视频图片信息
     * @param id 要修改的视频图片id
     * @param pictureDTO 视频图片信息
     */
    void update(String id ,VideoPictureDTO pictureDTO) throws SQLException;


    /**
     * 根据条件查询视频图片信息列表
     * @param pictureDTO 视频图片信息
     * @throws SQLException sql执行异常
     * return 查询结果
     */
    List<VideoPictureDTO> list(VideoPictureDTO pictureDTO) throws SQLException;

    /**
     * 根据id删除视频图片信息
     * @param id 视频图片id
     * @throws SQLException sql执行异常
     */
    void delete(String id) throws SQLException;

    /**
     * 根据id批量删除视频图片信息
     * @param ids 视频图片id列表
     * @throws SQLException sql执行异常
     */
    void deleteBatch(List<String> ids) throws SQLException;


    int count(VideoPictureDTO pictureDTO) throws SQLException;

}
