package com.video.dto.business;

import lombok.Data;

/**
 * description: 小程序查询结果
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/6 17:49
 */
@Data
public class AppQueryResult {

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 图片地址链接
     */
    private String picUrl;
}
