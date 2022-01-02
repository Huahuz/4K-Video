package com.video.service.impl;

import com.video.dto.business.VideoFilterCfgDTO;
import com.video.entity.VideoFilterCfg;
import com.video.mapper.VideoFilterCfgMapper;
import com.video.service.FilterConfigService;
import com.video.util.TransformUtil;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: 过滤项配置接口实现类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 22:54
 */
@Service
public class FilterConfigServiceImpl implements FilterConfigService {

    @Resource
    private VideoFilterCfgMapper cfgMapper;

    @Override
    public void add(VideoFilterCfgDTO cfgDTO) throws SQLException {
        cfgMapper.add(cfgDTO);
    }

    @Override
    public void update(VideoFilterCfgDTO cfgDTO) throws SQLException {
        cfgMapper.updateById(cfgDTO);
    }

    @Override
    public void delete(String id) throws SQLException {
        List<Long> childIds = cfgMapper.getChildIdByParentId(id);
        if (CollectionUtils.isEmpty(childIds)) {
            childIds = Lists.newArrayList(Long.parseLong(id));
        } else {
            childIds.add(Long.parseLong(id));
        }
        cfgMapper.deleteCfgByIds(childIds);
    }

    @Override
    public int count(VideoFilterCfgDTO cfgDTO) throws SQLException {
        return cfgMapper.count(cfgDTO);
    }

    @Override
    public List<VideoFilterCfgDTO> list(VideoFilterCfgDTO cfgDTO) throws SQLException {
        List<VideoFilterCfg> queryList = cfgMapper.list(cfgDTO);
        return TransformUtil.configTransformDTO(queryList);
    }

    @Override
    public List<VideoFilterCfgDTO> selectOptions(String parentId, String key, Integer type) throws SQLException {
        List<VideoFilterCfg> queryList = cfgMapper.selectOptions(parentId, key, type);
        return TransformUtil.configTransformDTO(queryList);
    }

    @Override
    public Map<String, String> filterDict() throws SQLException {
        return cfgMapper.selectOptions(null, null, 0)
                .stream()
                .collect(
                    Collectors.toMap(
                        VideoFilterCfg::getKey,
                        VideoFilterCfg::getValue,
                        (key1, key2) -> key2
                    )
                );
    }
}
