package com.video.dto.business;

import com.video.dto.business.app.Comment;
import com.video.dto.business.app.DownloadLink;
import com.video.dto.business.app.Picture;
import lombok.Data;

import java.util.List;

/**
 * description: 小程序详情信息展示
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/6 19:49
 */
@Data
public class AppDetailInfo {

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年份
     */
    private Integer years;

    /**
     * 简介
     */
    private String summary;

    /**
     * 是否关闭下载链接
     */
    private Integer offLink;

    /**
     * 是否关闭评论
     */
    private Integer offComment;

    /**
     * 评论列表
     */
    private List<Comment> commentList;

    /**
     * 图片列表
     */
    private List<Picture> pictureList;

    /**
     * 下载链接列表
     */
    private List<DownloadLink> linkList;
}
