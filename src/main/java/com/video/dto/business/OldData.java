package com.video.dto.business;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * description: TODO
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/11 15:56
 */
@Data
public class OldData {

    private LocalDateTime date;

    private String title;

    private int category;

    private String categoryName;

    private String content;

    private List<Map<String, String>> meta;

}
