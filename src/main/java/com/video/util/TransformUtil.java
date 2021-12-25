package com.video.util;

import com.video.dto.business.VideoCommentDTO;
import com.video.dto.business.VideoFilterCfgDTO;
import com.video.dto.business.VideoPictureDTO;
import com.video.entity.VideoComment;
import com.video.entity.VideoFilterCfg;
import com.video.entity.VideoPicture;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * description: 实体转换工具类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 18:39
 */
public interface TransformUtil {

    /**
     * 将评论实体类转换为传输类
     *
     * @param comments 评论实体
     * @return 传输类
     */
    static List<VideoCommentDTO> commentTransformDTO(List<VideoComment> comments) {
        List<VideoCommentDTO> dtoList = Lists.newArrayList();
        for (VideoComment comment : comments) {
            dtoList.add(VideoCommentDTO.builder()
                    .id(comment.getId().toString())
                    .videoId(comment.getVideoId().toString())
                    .userName(comment.getUserName())
                    .content(comment.getContent())
                    .score(comment.getScore())
                    .commentTime(comment.getCommentTime())
                    .isComplaint(comment.getIsComplaint())
                    .status(comment.getStatus())
                    .build());

        }
        return dtoList;
    }

    /**
     * 将过滤项配置实体类转换为传输类
     *
     * @param configs 评论实体
     * @return 传输类
     */
    static List<VideoFilterCfgDTO> configTransformDTO(List<VideoFilterCfg> configs) {
        List<VideoFilterCfgDTO> dtoList = Lists.newArrayList();
        for (VideoFilterCfg config : configs) {
            dtoList.add(VideoFilterCfgDTO.builder()
                    .id(config.getId().toString())
                    .parentId(config.getParentId().toString())
                    .key(config.getKey())
                    .value(config.getValue())
                    .orderNo(config.getOrderNo())
                    .isType(config.getIsType())
                    .build());

        }
        return dtoList;
    }


    /**
     * 将视频图片实体类转换为传输类
     *
     * @param pictures 视频图片实体
     * @return 传输类
     */
    static List<VideoPictureDTO>  pictureTransformDTO(List<VideoPicture> pictures){
        List<VideoPictureDTO> dtoList = Lists.newArrayList();
        for (VideoPicture picture : pictures) {
            dtoList.add(VideoPictureDTO.builder()
                    .id(picture.getId().toString())
                    .videoId(picture.getVideoId().toString())
                    .name(picture.getName())
                    .url(picture.getUrl())
                    .thumbnailsUrl(picture.getThumbnailsUrl())
                    .orderNo(picture.getOrderNo())
                    .createTime(picture.getCreateTime())
                    .updateTime(picture.getUpdateTime())
                    .build());

        }
        return dtoList;

    }



}
