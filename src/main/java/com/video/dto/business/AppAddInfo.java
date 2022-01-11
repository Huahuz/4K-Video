package com.video.dto.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:前台页面个人资源添加的输入
 *
 * @author fxx
 * @version 1.0
 * @since 2022/1/8 19:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppAddInfo {
    /**
     *视频id，填写表示更新已有视频的资源，未填写表示新增资源
     */
    String videoId;

    /**
     *视频名称
     */
    String name;

    /**
     *下载链接名称
     */
    String linkName;

    /**
     *	下载链接（磁力链接、网盘地址）
     */
    String linkUrl;

}
