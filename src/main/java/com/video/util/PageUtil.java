package com.video.util;

import lombok.SneakyThrows;

/**
 * description: 分页工具类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 19:03
 */
public interface PageUtil {

    /**
     * 根据所给信息计算开始索引
     * @param page 页码
     * @param size 页面大小
     * @return 起始索引
     */
    @SneakyThrows
    static int computeStartIdx(int page, int size) {
        if (page <= 0 || size <= 0) {
            throw new Exception("输入数据异常");
        } else {
            return (page - 1) * size;
        }
    }
}
