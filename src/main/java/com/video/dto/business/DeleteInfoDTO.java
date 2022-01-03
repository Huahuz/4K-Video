package com.video.dto.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 删除信息传输类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/3 21:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInfoDTO {

    /**
     * 视频id
     */
    private Long id;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 图片名称
     */
    private String picName;

    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 链接名称
     */
    private String linkName;

    /**
     * 链接地址
     */
    private String linkUrl;

    @Override
    public String toString() {
        return "被删除信息为: {" +
                "视频id: " + id +
                ", 视频名称: '" + videoName + '\'' +
                ", 图片名称: '" + picName + '\'' +
                ", 图片地址: " + picUrl + '\'' +
                ", 链接名称: " + linkName + '\'' +
                ", 链接地址: '" + linkUrl + '\'' +
                '}';
    }
}
