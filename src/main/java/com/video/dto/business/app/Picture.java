package com.video.dto.business.app;

import lombok.Data;

/**
 * description: 小程序图片类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/7 16:50
 */
@Data
public class Picture {
    /**
     * id
     */
    private String id;

    /**
     * 图片地址
     */
    private String url;
}
