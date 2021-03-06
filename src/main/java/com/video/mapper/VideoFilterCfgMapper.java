package com.video.mapper;

import com.video.dto.business.VideoFilterCfgDTO;
import com.video.entity.VideoFilterCfg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface VideoFilterCfgMapper {

    /**
     * 添加过滤项配置信息
     * @param cfgDTO 过滤项配置信息
     * @throws SQLException sql异常
     */
    void add(@Param("dto") VideoFilterCfgDTO cfgDTO) throws SQLException;

    /**
     * 修改过滤项配置信息
     * @param cfgDTO 过滤项配置信息
     * @throws SQLException sql异常
     */
    void updateById(@Param("dto") VideoFilterCfgDTO cfgDTO) throws SQLException;


    /**
     * 根据父配置id获取子配置id
     * @param id 父配置id
     * @return 子配置id
     * @throws SQLException sql异常
     */
    List<Long> getChildIdByParentId(@Param("id") String id) throws SQLException;

    /**
     * 删除过滤项配置信息
     * @param childIds 过滤项配置ids
     * @throws SQLException sql异常
     */
    void deleteCfgByIds(@Param("ids") List<Long> childIds) throws SQLException;

    /**
     * 根据条件统计过滤项配置数量
     * @param cfgDTO 过滤项配置信息
     * @return 统计结果
     * @throws SQLException sql异常
     */
    int count(@Param("dto") VideoFilterCfgDTO cfgDTO) throws SQLException;

    /**
     * 根据条件查询过滤项配置数量
     * @param cfgDTO 过滤项配置信息
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<VideoFilterCfg> list(@Param("dto") VideoFilterCfgDTO cfgDTO) throws SQLException;
}