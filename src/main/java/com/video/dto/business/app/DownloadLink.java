package com.video.dto.business.app;

import lombok.Data;

/**
 * description: 小程序下载链接类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/7 16:51
 */
@Data
public class DownloadLink {
    /**
     * id
     */
    private String id;

    /**
     * 链接名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String url;
}
