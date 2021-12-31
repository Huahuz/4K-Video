package com.video.dto.business;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.video.dto.common.PageParam;
import lombok.*;
import java.util.Date;


/**
 * description: 视频图片实体传输类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 15:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoPictureDTO extends PageParam{
    /**
     * 图片id
     */
    private String id;

    /**
     * 关联的视频id
     */
    private String videoId;

    /**
     *图片名称
     */
    private String name;

    /**
     *图片地址
     */
    private String url;

    /**
     * 略缩图地址
     */
    private String thumbnailsUrl;

    /**
     *同一视频图片顺序
     */
    private Integer orderNo;

    /**
     *创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     *更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
