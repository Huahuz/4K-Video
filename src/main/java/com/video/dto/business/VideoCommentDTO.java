package com.video.dto.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.video.dto.common.PageParam;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * description: 视频评论实体传输类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 15:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoCommentDTO extends PageParam {

    /**
     * id
     */
    private String id;

    /**
     * 视频id
     */
    private String videoId;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime commentTime;

    /**
     * 是否为侵权投诉评论
     */
    private Integer isComplaint;

    /**
     * 评论信息状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

}
