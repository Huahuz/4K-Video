package com.video.enums;

import lombok.Getter;

/**
 * description: http状态枚举类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 15:04
 */
@Getter
public enum HttpStatusEnum {

    SUCCESS(2000, "接口调用正常"),

    FAILURE(5000, "服务内部错误");

    private final Integer code;
    private final String msg;

    HttpStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
