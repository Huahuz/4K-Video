package com.video.dto.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 视频详细信息增删改中涉及的图片传输流
 *
 * @author fxx
 * @version 1.0
 * @since 2022/1/7 21:25
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoPicture {
    /**
     *视频图片id
     */
    String id;

    /**
     *视频图片url
     */
    String url;

    /**
     * 排序
     */
    private Integer orderNo;
}
