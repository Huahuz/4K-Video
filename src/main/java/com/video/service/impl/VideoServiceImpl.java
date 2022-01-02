package com.video.service.impl;

import com.video.dto.business.VideoDTO;
import com.video.dto.business.VideoResultDTO;
import com.video.mapper.VideoMapper;
import com.video.service.FilterConfigService;
import com.video.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
}
