package com.video.service;

import com.video.dto.business.VideoPictureDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * description: 图片服务接口类
 *
 * @author fxx
 * @version 1.0
 * @since 2021/12/27 16:54
 */
public interface VideoPictureService {

    /**
     * 新增视频图片
     * @param pictureDTO 视频图片信息
     */
    void add(VideoPictureDTO pictureDTO) throws SQLException;

    /**
     * 根据id修改视频图片信息
     * @param pictureDTO 视频图片信息
     */
    void update(VideoPictureDTO pictureDTO) throws SQLException;


    /**
     * 根据条件查询视频图片信息列表
     * @param pictureDTO 视频图片信息
     * @throws SQLException sql执行异常
     * return 查询结果
     */
    List<VideoPictureDTO> list(VideoPictureDTO pictureDTO) throws SQLException;


    /**
     * 根据名称模糊查询
     * @param pictureDTO
     * @return 操作结果
     */
    List<VideoPictureDTO> listName(VideoPictureDTO pictureDTO) throws  SQLException;



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
    void deleteBatch(String ids) throws SQLException;

    /**
     * 根据条件查询图片列表的数量
     * @param pictureDTO 筛选条件
     * @return 查询结果
     */
    int count(VideoPictureDTO pictureDTO) throws SQLException;



    /**
     * 根据模糊名称查询图片列表的数量
     * @param pictureDTO 筛选条件
     * @return 查询结果
     */
    int countName(VideoPictureDTO pictureDTO) throws SQLException;
}
