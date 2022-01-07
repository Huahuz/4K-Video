package com.video.dto.business;

import com.video.dto.common.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 小程序查询参数
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/6 18:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppQueryParam extends PageParam {

    /**
     * 名称
     */
    private String name;

    /**
     * 类别
     */
    private String category;

    /**
     * 类型
     */
    private String type;

    /**
     * 地区
     */
    private String region;

    /**
     * 是否按照评分排序 0否 1是
     */
    private Integer isScore;

    /**
     * 是否查找置顶数据 0否 1是
     */
    private Integer isTop;
}
