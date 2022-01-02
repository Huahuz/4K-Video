package com.video.dto.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.video.dto.common.PageParam;
import lombok.*;

import java.time.LocalDateTime;

/**
 * description: 视频传输实体类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/2 15:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO extends PageParam {

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
     * 视频简介
     */
    private String summary;

    /**
     * 开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;
}
