package com.video.service.impl;

import com.video.dto.business.VideoSysCfgDTO;
import com.video.entity.VideoFilterCfg;
import com.video.entity.VideoSysCfg;
import com.video.mapper.VideoSysCfgMapper;
import com.video.service.SysConfigService;
import com.video.util.TransformUtil;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * description: 系统配置接口实现类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 22:54
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Resource
    private VideoSysCfgMapper cfgMapper;

    @Override
    public void add(VideoSysCfgDTO cfgDTO) throws SQLException {
        cfgMapper.add(cfgDTO);
    }

    @Override
    public void update(VideoSysCfgDTO cfgDTO) throws SQLException {
        cfgMapper.updateById(cfgDTO);
    }

    @Override
    public void delete(String id) throws SQLException {
        cfgMapper.deleteById(Long.parseLong(id));
    }

    @Override
    public List<VideoSysCfgDTO> list() throws SQLException {
        List<VideoSysCfg> queryList = cfgMapper.list();
        return TransformUtil.sysCfgTransformDTO(queryList);
    }
}
