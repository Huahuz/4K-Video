package com.video.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Video {

    /**
     * 视频id
     */
    private Long id;

    /**
     * 视频名称
     */
    private String name;

    /**
     * 视频类别
     */
    private String category;

    /**
     * 视频类型
     */
    private String type;

    /**
     * 地区
     */
    private String region;

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String comments;

    /**
     * 视频简介
     */
    private String summary;
}