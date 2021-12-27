package com.video.entity;

import lombok.Data;

@Data
public class VideoSysCfg {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 系统配置项-key
     */
    private String key;

    /**
     * 系统配置项-value
     */
    private String value;

    /**
     * 系统配置项-状态
     */
    private Integer status;

    /**
     * 排序顺序
     */
    private Integer orderNo;
}