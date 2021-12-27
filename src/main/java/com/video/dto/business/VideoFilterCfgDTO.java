package com.video.dto.business;

import com.video.dto.common.PageParam;
import lombok.*;

/**
 * description: 视频过滤项配置传输类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 22:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoFilterCfgDTO extends PageParam {
    /**
     * id
     */
    private String id;

    /**
     * 父配置项id
     */
    private String parentId;

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
