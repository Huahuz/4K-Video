package com.video.service.impl;

import com.video.dto.business.VideoCommentDTO;
import com.video.entity.VideoComment;
import com.video.mapper.VideoCommentMapper;
import com.video.service.CommentService;
import com.video.util.TransformUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * description: 评论服务接口实现类
 *
 * @author wxy
 * @version 1.0
 * @since 2021/12/21 16:54
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private VideoCommentMapper commentMapper;

    @Override
    public void add(VideoCommentDTO commentDTO) throws SQLException {
        // 1.评论信息入库
        commentMapper.add(commentDTO);
        // 2.判断当前评论是否为投诉侵权信息，若是，则通知管理员进行处理
        // TODO 通知接口暂时未定，可能用websocket来进行消息的推送，等待后续实现
    }

    @Override
    public void delete(String id) throws SQLException {
        commentMapper.delete(id);
    }

    @Override
    public void switchStatus(String id, Integer status) throws SQLException {
        commentMapper.switchStatus(id, status);
    }

    @Override
    public List<VideoCommentDTO> list(VideoCommentDTO commentDTO) throws SQLException {
        List<VideoComment> queryList = commentMapper.list(commentDTO);
        return TransformUtil.commentTransformDTO(queryList);
    }

    @Override
    public int count(VideoCommentDTO commentDTO) throws SQLException {
        return commentMapper.count(commentDTO);
    }
}
