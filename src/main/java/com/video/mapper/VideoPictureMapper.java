package com.video.mapper;

import com.video.dto.business.VideoPictureDTO;
import com.video.entity.VideoPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface VideoPictureMapper {


    /**
     * 添加视频图片信息
     * @param pictureDTO 视频图片信息
     * @throws SQLException sql执行异常
     */

    void add(@Param("dto") VideoPictureDTO pictureDTO) throws SQLException;

    /**
     * 根据id修改视频图片信息
     * @param pictureDTO 视频图片信息
     * @throws SQLException sql执行异常
     */
    void update(@Param("dto") VideoPictureDTO pictureDTO) throws SQLException;


    /**
     * 根据条件查询视频图片信息列表
     * @param pictureDTO 视频图片信息
     * @throws SQLException sql执行异常
     * return 查询结果
     */
    List<VideoPicture> list(@Param("dto") VideoPictureDTO pictureDTO) throws SQLException;


    /**
     * 根据name进行模糊查询
     * @param pictureDTO 输入的名称及筛选条件
     * @return 操作结果
     */
    List<VideoPicture> listName(@Param("dto") VideoPictureDTO pictureDTO) throws SQLException;


    /**
     * 根据id删除视频图片信息
     * @param id 视频图片id
     * @throws SQLException sql执行异常
     */
    void delete(@Param("id") String id) throws SQLException;

    /**
     * 根据id批量删除视频图片信息
     * @param ids 视频图片id列表
     * @throws SQLException sql执行异常
     */
    void deleteBatch(@Param("ids") String[] ids) throws SQLException;

    /**
     * 根据条件查询视频图片的数量
     * @param pictureDTO 筛选信息
     * @throws SQLException sql执行异常
     */
    int count(@Param("dto") VideoPictureDTO pictureDTO) throws SQLException;

    /**
     * 根据模糊名称条件查询视频图片的数量
     * @param pictureDTO 筛选信息
     * @throws SQLException sql执行异常
     */
    int countName(@Param("dto") VideoPictureDTO pictureDTO) throws SQLException;
}