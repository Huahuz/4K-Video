package com.video.mapper;

import com.video.dto.business.VideoSysCfgDTO;
import com.video.entity.VideoSysCfg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface VideoSysCfgMapper {

    /**
     * 添加系统配置信息
     * @param cfgDTO 系统配置信息
     * @throws SQLException sql异常
     */
    void add(@Param("dto") VideoSysCfgDTO cfgDTO) throws SQLException;

    /**
     * 修改系统配置信息
     * @param cfgDTO 系统配置信息
     * @throws SQLException sql异常
     */
    void updateById(@Param("dto") VideoSysCfgDTO cfgDTO) throws SQLException;


    /**
     * 删除系统配置信息
     * @param id 系统配置ids
     * @throws SQLException sql异常
     */
    void deleteById(@Param("id") Long id) throws SQLException;


    /**
     * 根据条件查询系统配置数量
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<VideoSysCfg> list() throws SQLException;
}