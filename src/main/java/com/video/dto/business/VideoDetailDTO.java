package com.video.dto.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;


/**
 * description: 单条视频信息详细的实体传输类
 *
 * @author fxx
 * @version 1.0
 * @since 2022/1/3 17:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDetailDTO{

    /**
     * 视频id
     */
    private Long id;

    /**
     * 视频名称
     */
    private String name;

    /**
     * 视频类别key
     */
    private String category;

    /**
     * 视频类别内容
     */
    private String categoryName;


    /**
     * 视频类型key
     */
    private String type;

    /**
     * 视频类型内容
     */
    private String typeName;

    /**
     * 地区key
     */
    private String region;

    /**
     * 地区内容
     */
    private String regionName;

    /**
     * 视频年份
     */
    private Integer years;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 视频简介
     */
    private String summary;

    /**
     *创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    /**
     *更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 视频对应的图片信息
     */
    private List<InfoPicture> infoPictureList;


    /**
     * 视频对应的链接信息
     */
    private List<InfoDownLoadLink> infoDownLoadLinkList;


}
