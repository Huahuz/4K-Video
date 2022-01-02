package com.video.dto.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 视频信息查询结果实体传输类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/2 16:49
 */
@Data
public class VideoResultDTO {
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
     * 视频类别名称
     */
    private String categoryName;

    /**
     * 视频类型
     */
    private String type;

    /**
     * 视频类型名称
     */
    private String typeName;

    /**
     * 地区
     */
    private String region;

    /**
     * 地区名称
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
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    private String comments;
}
