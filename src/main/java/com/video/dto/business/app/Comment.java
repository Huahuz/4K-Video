package com.video.dto.business.app;

import lombok.Data;

/**
 * description: 小程序返回评论类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/7 16:49
 */
@Data
public class Comment {
    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评分
     */
    private Integer score;
}
