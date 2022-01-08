package com.video.dto.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:视频详细信息增删改中涉及的下载链接传输流
 *
 * @author fxx
 * @version 1.0
 * @since 2022/1/7 21:28
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoDownLoadLink {
    /**
     *下载链接id
     */
    String id;

    /**
     *视频图片name
     */
    String name;

    /**
     *视频图片url
     */
    String url;
}
