package com.video.dto.common;

import lombok.Data;

/**
 * description: 分页信息包装类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 16:06
 */
@Data
public class Page <T> {

    /**
     * 页码
     */
    private Integer page;

    /**
     * 页面大小
     */
    private Integer size;

    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 数据
     */
    private T data;

    public Page(Integer page, Integer size, Integer total, T data) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
    }

    /**
     * 封装分页信息方法
     * @param page 页码
     * @param size 页面大小
     * @param total 总记录数
     * @param data 数据
     * @param <T> 泛型
     * @return 分页信息
     */
    public static <T> Page<T> pageInfo(int page, int size, int total, T data) {
        return new Page<>(page, size, total, data);
    }
}
