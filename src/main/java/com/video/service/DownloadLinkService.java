package com.video.service;

import com.video.dto.business.VideoDownloadLinkDTO;
import com.video.entity.VideoDownloadLink;

import java.sql.SQLException;
import java.util.List;

/**
 * description:
 *
 * @author fxx
 * @version 1.0
 * @since 2021/12/29 11:32
 */
public interface DownloadLinkService {

    /**
     * 新增下载链接
     * @param downloadLinkServiceDTO 下载链接信息
     */
    void add(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException;

    /**
     * 根据id修改下载连接的内容
     * @param id 修改的id
     * @param status 修改后的状态
     */
    void switchStatus(String id, Integer status) throws SQLException;

    /**
     * 根据条件查询下载链接
     * @param downloadLinkServiceDTO 筛选条件
     */
    List<VideoDownloadLinkDTO> list(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException;

    /**
     * 根据条件查询下载链接的数量
     * @param downloadLinkServiceDTO 筛选条件
     */
    int count(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException;

    /**
     * 根据id删除下载链接信息
     * @param id 要删除的id
     */
    void delete(String id) throws SQLException;

    /**
     * 根据id批量删除下载链接
     * @param ids 要删除的ids
     */
    void deleteBatch(String ids) throws SQLException;


}
