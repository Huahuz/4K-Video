package com.video.service.impl;

import com.video.dto.business.DeleteInfoDTO;
import com.video.dto.business.VideoDTO;
import com.video.dto.business.VideoResultDTO;
import com.video.mapper.VideoMapper;
import com.video.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final static Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private FilterConfigService cfgService;

    @Resource
    private CommentService commentService;

    @Resource
    private VideoPictureService pictureService;

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
    public void top(String id, String status) throws SQLException {
        videoMapper.top(id, status);
    }

    @Override
    public void switchStatusBatch(String ids, String status) throws SQLException {
        String[] idArr = ids.split(",");
        videoMapper.switchStatusBatch(idArr, status);
    }

    @Override
    public void switchStatus(String id, String status) throws SQLException {
        switchStatusBatch(id, status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id){
        this.deleteBatch(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids) {
        LOGGER.info("开始删除id为：[{}]的视频。", ids);
        // 删除视频并级联删除其对应的评论、图片信息、下载链接信息。
        // 对图片信息和下载链接信息进行日志记录。
        // 当执行异常时，对数据进行回滚操作。
        List<DeleteInfoDTO> deleteInfo;
        try {
            String[] idArr = ids.split(",");

            //记录被删除的信息
            deleteInfo = videoMapper.getDeleteInfo(idArr);

            // 依次删除评论、图片、下载链接、视频信息
            commentService.deleteByVideoId(idArr);
            pictureService.deleteByVideoId(idArr);
            downloadLinkService.deleteByVideoId(idArr);
            videoMapper.deleteBatch(idArr);
        } catch (SQLException exception) {
            LOGGER.error("删除视频操作失败" + exception);
            throw new RuntimeException("删除视频操作失败");
        }
        LOGGER.info("删除视频的关联信息如下：[{}]", deleteInfo);
        LOGGER.info("id为：[{}]的视频删除成功。", ids);
    }
}
