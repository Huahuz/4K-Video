package com.video.service.impl;

import com.video.dto.business.VideoDownloadLinkDTO;
import com.video.entity.VideoDownloadLink;
import com.video.mapper.VideoDownloadLinkMapper;
import com.video.service.DownloadLinkService;
import com.video.util.TransformUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * description:
 *
 * @author fxx
 * @version 1.0
 * @since 2021/12/29 11:55
 */
public class DownloadLinkServiceImpl implements DownloadLinkService {

    @Resource
    public VideoDownloadLinkMapper videoDownloadLinkMapper;


    @Override
    public void add(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException {
        videoDownloadLinkMapper.add(downloadLinkServiceDTO);
    }

    @Override
    public void update(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException {
        videoDownloadLinkMapper.update(downloadLinkServiceDTO);

    }

    @Override
    public List<VideoDownloadLinkDTO> list(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException {
        List<VideoDownloadLink> list = videoDownloadLinkMapper.list(downloadLinkServiceDTO);
        return TransformUtil.downloadLinkTransformDTO(list);
    }

    @Override
    public int count(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException{
        int count = videoDownloadLinkMapper.count(downloadLinkServiceDTO);
        return count;
    }

    @Override
    public void delete(String id) throws SQLException{
        videoDownloadLinkMapper.delete(id);
    }

    @Override
    public void deleteBatch(String ids) throws SQLException {
        String[] strings = ids.split(",");
        videoDownloadLinkMapper.deleteBatch(strings);

    }
}
