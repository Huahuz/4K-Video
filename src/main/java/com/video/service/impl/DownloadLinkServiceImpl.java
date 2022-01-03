package com.video.service.impl;

import com.video.dto.business.VideoDownloadLinkDTO;
import com.video.entity.VideoDownloadLink;
import com.video.mapper.VideoDownloadLinkMapper;
import com.video.service.DownloadLinkService;
import com.video.util.TransformUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * description: 下载链接实现类
 *
 * @author fxx
 * @version 1.0
 * @since 2021/12/29 11:55
 */
@Service
public class DownloadLinkServiceImpl implements DownloadLinkService {

    @Resource
    public VideoDownloadLinkMapper videoDownloadLinkMapper;


    @Override
    public void add(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException {
        videoDownloadLinkMapper.add(downloadLinkServiceDTO);
    }

    @Override
    public List<VideoDownloadLinkDTO> list(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException {
        List<VideoDownloadLink> list = videoDownloadLinkMapper.list(downloadLinkServiceDTO);
        return TransformUtil.downloadLinkTransformDTO(list);
    }

    @Override
    public int count(VideoDownloadLinkDTO downloadLinkServiceDTO) throws SQLException{
        return videoDownloadLinkMapper.count(downloadLinkServiceDTO);
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

    @Override
    public void update(VideoDownloadLinkDTO linkDTO) throws SQLException {
        if (Objects.isNull(linkDTO.getOrderNo())) {
            linkDTO.setOrderNo(0);
        }
        if (Objects.isNull(linkDTO.getStatus())) {
            linkDTO.setStatus(0);
        }
        videoDownloadLinkMapper.update(linkDTO);
    }

    @Override
    public void deleteByVideoId(String[] idArr) throws SQLException {
        videoDownloadLinkMapper.deleteByVideoId(idArr);
    }
}
