package com.video.service.impl;

import com.video.dto.business.VideoPictureDTO;
import com.video.entity.VideoPicture;
import com.video.mapper.VideoPictureMapper;
import com.video.service.VideoPictureService;
import com.video.util.TransformUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;


@Service
public class VideoPictureServiceImp implements VideoPictureService {

    @Resource
    private VideoPictureMapper videoPictureMapper;

    @Override
    public void add(VideoPictureDTO pictureDTO) throws SQLException {
        videoPictureMapper.add(pictureDTO);

    }

    @Override
    public void update(String id, VideoPictureDTO pictureDTO) throws SQLException {
        videoPictureMapper.update(id,pictureDTO);

    }

    @Override
    public List<VideoPictureDTO> list(VideoPictureDTO pictureDTO) throws SQLException {
        List<VideoPicture> list = videoPictureMapper.list(pictureDTO);
        return TransformUtil.pictureTransformDTO(list);
    }

    @Override
    public void delete(String id) throws SQLException {
        videoPictureMapper.delete(id);

    }

    @Override
    public void deleteBatch(String ids) throws SQLException {
        String[] idArr = ids.split(",");
        videoPictureMapper.deleteBatch(idArr);
    }

    @Override
    public int count(VideoPictureDTO pictureDTO) throws SQLException {
        int count = videoPictureMapper.count(pictureDTO);
        return count;
    }
}
