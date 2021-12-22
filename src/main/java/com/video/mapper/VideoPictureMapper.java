package com.video.mapper;

import com.video.entity.VideoPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface VideoPictureMapper {


    /**
     * 添加视频图片信息
     * @param vpi 视频图片信息
     * @throws SQLException sql执行异常
     */

    void add(@Param("vpi") VideoPicture vpi) throws SQLException;

    /**
     * 根据id修改视频图片信息
     * @param id 要修改的视频图片id
     * @param vpi 视频图片信息
     * @throws SQLException sql执行异常
     */
    void update(@Param("id") Integer id ,@Param("vpi") VideoPicture vpi) throws SQLException;


    /**
     * 根据条件查询视频图片信息列表
     * @param vpi 视频图片信息
     * @throws SQLException sql执行异常
     * return 查询结果
     */

    List<VideoPicture> findByCondition(@Param("vpi") VideoPicture vpi) throws SQLException;

    /**
     * 根据id删除视频图片信息
     * @param id 视频图片id
     * @throws SQLException sql执行异常
     */
    void deleteById(@Param("id") Integer id) throws SQLException;

    /**
     * 根据id批量删除视频图片信息
     * @param ids 视频图片id列表
     * @throws SQLException sql执行异常
     */
    void deleteMore(@Param("ids") List<Integer> ids) throws SQLException;
}