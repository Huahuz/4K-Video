package com.video.dto.business;

import lombok.*;

/**
 * description: 视频系统配置传输类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 22:47
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoSysCfgDTO {
    /**
     * id
     */
    private String id;

    /**
     * 配置项key
     */
    private String key;

    /**
     * 配置项value
     */
    private String value;

    /**
     * 系统配置项-状态
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer orderNo;
}
