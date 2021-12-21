package com.video.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoComment {
    /**
     * id
     */

    private Long id;

    /**
     * 视频id
     */
    private Long videoId;

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

    /**
     * 评论时间
     */
    private LocalDateTime commentTime;

    /**
     * 是否为侵权投诉评论
     */
    private Integer isComplaint;

    /**
     * 评论信息状态
     */
    private Integer status;
}