package com.video.dto.business;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.video.dto.common.PageParam;
import lombok.*;
import org.apache.ibatis.annotations.Param;

/**
 * description: 视频下载连接传输实体类
 *
 * @author fxx
 * @version 1.0
 * @since 2021/12/29 14:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDownloadLinkDTO extends PageParam{

    /**
     * id
     */
    private String id;

    /**
     * 关联的视频id
     */
    private String videoId;

    /**
     * 链接名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 同一视频链接顺序
     */
    private Integer orderNo;

    /**
     * 链接状态，未发布、已发布、已冻结
     */
    private Integer status;

}
