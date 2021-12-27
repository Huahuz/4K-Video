package com.video.dto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * description: 分页参数类，如需要进行分页，则继承该类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 15:25
 */
@Data
public abstract class PageParam implements Serializable {
    /**
     * 当前页码
     */
    private Integer page = 0;

    /**
     * 页面大小x
     */
    private Integer pageSize = 9;

    /**
     * 开始索引
     */
    private Integer startIdx;

    /**
     * 总记录数
     */
    private Integer total;
}
