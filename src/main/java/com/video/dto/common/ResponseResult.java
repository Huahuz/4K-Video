package com.video.dto.common;

import com.video.enums.HttpStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * description: 通用的实体返回包装类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 14:50
 */
@Data
public class ResponseResult<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 传输数据
     */
    private T data;

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 无传输对象调用成功
     * @param <T> 泛型
     * @return 通用传输类
     */
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    /**
     * 有传输对象调用成功
     * @param data 传输数据
     * @param <T> 泛型
     * @return 通用传输类
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 无传输对象调用失败
     * @param <T> 泛型
     * @return 通用传输类
     */
    public static <T> ResponseResult<T> failure() {
        return new ResponseResult<>(HttpStatusEnum.FAILURE.getCode(), HttpStatusEnum.FAILURE.getMsg(), null);
    }

}
