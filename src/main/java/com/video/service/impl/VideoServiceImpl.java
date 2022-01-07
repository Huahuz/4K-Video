package com.video.service.impl;

import com.video.dto.business.*;
import com.video.mapper.VideoMapper;
import com.video.service.DownloadLinkService;
import com.video.service.FilterConfigService;
import com.video.service.VideoPictureService;
import com.video.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description: 视频服务实现类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/2 16:02
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private FilterConfigService cfgService;

    @Resource
    private VideoPictureService videoPictureService;

    @Resource
    private DownloadLinkService downloadLinkService;

    @Override
    public List<VideoResultDTO> list(VideoDTO videoDTO) throws SQLException {
        // 根据条件查询列表数据，并联表翻译地区与类别字段
        List<VideoResultDTO> queryList = videoMapper.list(videoDTO);
        // 将查询到的数据的类型字段进行格式组装并进行翻译
        // type: love,action,story;  dict: key:value
        Map<String, String> filterDict = cfgService.filterDict();

        queryList.forEach(res -> {
            if (StringUtils.isBlank(res.getType())) {
                return;
            }
            String[] types = res.getType().split(",");
            StringBuilder typeName = new StringBuilder();
            for (String type : types) {
                typeName.append(filterDict.get(type)).append("/");
            }
            res.setTypeName(typeName.deleteCharAt(typeName.length() - 1).toString());
        });
        return queryList;
    }

    @Override
    public int count(VideoDTO videoDTO) throws SQLException {
        return videoMapper.count(videoDTO);
    }

    @Override
    public VideoDetailDTO detail(String id) throws SQLException {
        VideoDetailDTO detailDTO = videoMapper.detail(id);
        Map<String, String> filterDict = cfgService.filterDict();
        if (detailDTO==null||StringUtils.isBlank(detailDTO.getType())) {
            return detailDTO;
        }
        String[] types = detailDTO.getType().split(",");
        StringBuilder typeName = new StringBuilder();
        for (String type : types) {
            typeName.append(filterDict.get(type)).append("/");
        }
        detailDTO.setTypeName(typeName.deleteCharAt(typeName.length() - 1).toString());
        return detailDTO;
    }

    @Transactional
    @Override
    public void add(VideoDetailDTO dto) throws SQLException {
        videoMapper.add(dto);

        Long id = dto.getId();
        String s = String.valueOf(dto.getId());

        List<InfoPicture> infoPictureList = dto.getInfoPictureList();
        for (InfoPicture infoPicture : infoPictureList) {
            VideoPictureDTO videoPictureDTO = new VideoPictureDTO();
            videoPictureDTO.setId(infoPicture.getId());
            videoPictureDTO.setVideoId(s);
            videoPictureDTO.setName(" ");
            videoPictureDTO.setUrl(infoPicture.getUrl());
            videoPictureService.add(videoPictureDTO);

        }

        List<InfoDownLoadLink> infoDownLoadLinkList = dto.getInfoDownLoadLinkList();
        for (InfoDownLoadLink infoDownLoadLink : infoDownLoadLinkList) {
            VideoDownloadLinkDTO videoDownloadLinkDTO = new VideoDownloadLinkDTO();
            videoDownloadLinkDTO.setId(infoDownLoadLink.getId());
            videoDownloadLinkDTO.setVideoId(s);
            videoDownloadLinkDTO.setName(infoDownLoadLink.getName());
            videoDownloadLinkDTO.setUrl(infoDownLoadLink.getUrl());
            videoDownloadLinkDTO.setStatus(0);
            videoDownloadLinkDTO.setOrderNo(0);
            downloadLinkService.add(videoDownloadLinkDTO);
        }
    }

    @Transactional
    @Override
    public void update(VideoDetailDTO dto) throws SQLException {
        videoMapper.update(dto);
        //获取id值
        Long id = dto.getId();
        String s = String.valueOf(dto.getId());
        //读取要更新的图片信息

        List<InfoPicture> infoPictureList = dto.getInfoPictureList();
        for (InfoPicture infoPicture : infoPictureList) {
            VideoPictureDTO videoPictureDTO = new VideoPictureDTO();
            videoPictureDTO.setId(infoPicture.getId());
            videoPictureDTO.setVideoId(s);
            videoPictureDTO.setUrl(infoPicture.getUrl());
            videoPictureService.update(videoPictureDTO);

        }
        List<InfoDownLoadLink> infoDownLoadLinkList = dto.getInfoDownLoadLinkList();
        for (InfoDownLoadLink infoDownLoadLink : infoDownLoadLinkList) {
            VideoDownloadLinkDTO videoDownloadLinkDTO = new VideoDownloadLinkDTO();
            videoDownloadLinkDTO.setId(infoDownLoadLink.getId());
            videoDownloadLinkDTO.setVideoId(s);
            videoDownloadLinkDTO.setName(infoDownLoadLink.getName());
            videoDownloadLinkDTO.setUrl(infoDownLoadLink.getUrl());
            downloadLinkService.update(videoDownloadLinkDTO);
        }
    }
}
