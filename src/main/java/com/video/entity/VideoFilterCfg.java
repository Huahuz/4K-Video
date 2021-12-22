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
     * 是否类别配置项0否 1是
     */
    private Integer isType;

}