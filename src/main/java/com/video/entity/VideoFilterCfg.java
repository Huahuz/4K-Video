package com.video.entity;

import lombok.Data;

@Data
public class VideoFilterCfg {
    /**
     * id
     */
    private Long id;

    /**
     * 父配置项id
     */
    private Long parentId;

    /**
     * 配置项key
     */
    private String key;

    /**
     * 配置项value
     */
    private String value;

    /**
     * 排序
     */
    private Integer orderNo;

    /**
     * 配置项类型，0为类型项配置 1为类别项配置 2为地区项配置
     */
    private Integer type;

}