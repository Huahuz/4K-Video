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

    /**
     *
     * @param parentId 父配置id
     * @param key 配置项key
     * @param type 配置项类型
     * @return 查询结果
     * @throws SQLException sql异常
     */
    List<VideoFilterCfg> selectOptions(@Param("parentId") String parentId, @Param("key") String key, @Param("type") Integer type) throws SQLException;

    /**
     * 根据id查询配置信息
     * @param id 配置id
     * @return 配置信息
     */
    VideoFilterCfg getInfoById(@Param("id") String id);

    /**
     * 更新视频表的类型键的值
     * @param oldKey 旧值
     * @param newKey 新值
     */
    void updateVideoType(@Param("oldKey") String oldKey, @Param("newKey") String newKey);

    /**
     * 更新视频表的类型键的值
     * @param oldKey 旧值
     * @param newKey 新值
     */
    void updateVideoCategory(@Param("oldKey") String oldKey, @Param("newKey") String newKey);

    /**
     * 更新视频表的类型键的值
     * @param oldKey 旧值
     * @param newKey 新值
     */
    void updateVideoRegion(@Param("oldKey") String oldKey, @Param("newKey") String newKey);
}