package com.video.service.impl;

import com.video.dto.business.*;
import com.video.entity.VideoSysCfg;
import com.video.mapper.VideoMapper;
import com.video.mapper.VideoSysCfgMapper;
import com.video.service.*;
import com.video.util.PageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * description: 视频服务实现类
 *
 * @author wxy
 * @version 1.0
 * @since 2022/1/2 16:02
 */
@Service
public class VideoServiceImpl implements VideoService {

    private final static Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private FilterConfigService cfgService;

    @Resource
    private CommentService commentService;

    @Resource
    private VideoPictureService pictureService;

    @Resource
    private DownloadLinkService downloadLinkService;

    @Resource
    private VideoSysCfgMapper sysCfgMapper;

    @Override
    public List<VideoResultDTO> list(VideoDTO videoDTO) throws SQLException {
        // 根据条件查询列表数据，并联表翻译地区与类别字段
        List<VideoResultDTO> queryList = videoMapper.list(videoDTO);
        // 将查询到的数据的类型字段进行格式组装并进行翻译
        // type: love,action,story;  dict: key:value
        Map<String, String> filterDict = cfgService.filterDict();

        queryList.forEach(res -> {
            if (StringUtils.isBlank(res.getType())) {
                return;
            }
            String[] types = res.getType().split(",");
            StringBuilder typeName = new StringBuilder();
            for (String type : types) {
                typeName.append(filterDict.get(type)).append("/");
            }
            res.setTypeName(typeName.deleteCharAt(typeName.length() - 1).toString());
        });
        return queryList;
    }

    @Override
    public int count(VideoDTO videoDTO) throws SQLException {
        return videoMapper.count(videoDTO);
    }

    @Override
    public void top(String id, String status) throws SQLException {
        videoMapper.top(id, status);
    }

    @Override
    public void switchStatusBatch(String ids, String status) throws SQLException {
        String[] idArr = ids.split(",");
        videoMapper.switchStatusBatch(idArr, status);
    }

    @Override
    public void switchStatus(String id, String status) throws SQLException {
        switchStatusBatch(id, status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id){
        this.deleteBatch(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids) {
        LOGGER.info("开始删除id为：[{}]的视频。", ids);
        // 删除视频并级联删除其对应的评论、图片信息、下载链接信息。
        // 对图片信息和下载链接信息进行日志记录。
        // 当执行异常时，对数据进行回滚操作。
        List<DeleteInfoDTO> deleteInfo;
        try {
            String[] idArr = ids.split(",");

            //记录被删除的信息
            deleteInfo = videoMapper.getDeleteInfo(idArr);

            // 依次删除评论、图片、下载链接、视频信息
            commentService.deleteByVideoId(idArr);
            pictureService.deleteByVideoId(idArr);
            downloadLinkService.deleteByVideoId(idArr);
            videoMapper.deleteBatch(idArr);
        } catch (SQLException exception) {
            LOGGER.error("删除视频操作失败" + exception);
            throw new RuntimeException("删除视频操作失败");
        }
        LOGGER.info("删除视频的关联信息如下：[{}]", deleteInfo);
        LOGGER.info("id为：[{}]的视频删除成功。", ids);
    }

    @Override
    public VideoDetailDTO detail(String id) throws SQLException {
        VideoDetailDTO detailDTO = videoMapper.detail(id);
        Map<String, String> filterDict = cfgService.filterDict();
        if (detailDTO==null||StringUtils.isBlank(detailDTO.getType())) {
            return detailDTO;
        }
        String[] types = detailDTO.getType().split(",");
        StringBuilder typeName = new StringBuilder();
        for (String type : types) {
            typeName.append(filterDict.get(type)).append("/");
        }
        detailDTO.setTypeName(typeName.deleteCharAt(typeName.length() - 1).toString());
        return detailDTO;
    }

    @Transactional
    @Override
    public void add(VideoDetailDTO dto) throws SQLException {
        videoMapper.add(dto);

        Long id = dto.getId();
        String s = String.valueOf(dto.getId());

        List<InfoPicture> infoPictureList = dto.getPictureList();
        if(infoPictureList!=null){
            for (InfoPicture infoPicture : infoPictureList) {
                VideoPictureDTO videoPictureDTO = new VideoPictureDTO();
                videoPictureDTO.setId(infoPicture.getId());
                videoPictureDTO.setVideoId(s);
                videoPictureDTO.setName(" ");
                videoPictureDTO.setUrl(infoPicture.getUrl());
                videoPictureDTO.setOrderNo(Objects.isNull(infoPicture.getOrderNo()) ? 0 : infoPicture.getOrderNo());
                pictureService.add(videoPictureDTO);

            }
        }


        List<InfoDownLoadLink> infoDownLoadLinkList = dto.getLinkList();
        if (infoDownLoadLinkList!=null){
            for (InfoDownLoadLink infoDownLoadLink : infoDownLoadLinkList) {
                VideoDownloadLinkDTO videoDownloadLinkDTO = new VideoDownloadLinkDTO();
                videoDownloadLinkDTO.setId(infoDownLoadLink.getId());
                videoDownloadLinkDTO.setVideoId(s);
                videoDownloadLinkDTO.setName(infoDownLoadLink.getName());
                videoDownloadLinkDTO.setUrl(StringUtils.isNotBlank(infoDownLoadLink.getUrl()) ? infoDownLoadLink.getUrl() : "未知");
                videoDownloadLinkDTO.setStatus(0);
                videoDownloadLinkDTO.setOrderNo(infoDownLoadLink.getOrderNo() == null ? 0 : infoDownLoadLink.getOrderNo());
                downloadLinkService.add(videoDownloadLinkDTO);
            }
        }

    }

    @Transactional
    @Override
    public void update(VideoDetailDTO dto) throws SQLException {
        videoMapper.update(dto);
        //获取id值
        Long id = dto.getId();
        String s = String.valueOf(dto.getId());
        //读取要更新的图片信息

        List<InfoPicture> infoPictureList = dto.getPictureList();
        if (infoPictureList!=null){
            for (InfoPicture infoPicture : infoPictureList) {
                VideoPictureDTO videoPictureDTO = new VideoPictureDTO();
                videoPictureDTO.setId(infoPicture.getId());
                videoPictureDTO.setVideoId(s);
                videoPictureDTO.setUrl(infoPicture.getUrl());
                String id1 = infoPicture.getId();
                if (id1==""){
                    break;
                }
                int i = Integer.parseInt(infoPicture.getId());
                if(i<0){
                    videoPictureDTO.setName(" ");
                    pictureService.add(videoPictureDTO);
                }else{
                    pictureService.update(videoPictureDTO);

                }
            }

        }

        List<InfoDownLoadLink> infoDownLoadLinkList = dto.getLinkList();
        if (infoDownLoadLinkList !=null) {
            for (InfoDownLoadLink infoDownLoadLink : infoDownLoadLinkList) {
                VideoDownloadLinkDTO videoDownloadLinkDTO = new VideoDownloadLinkDTO();
                videoDownloadLinkDTO.setId(infoDownLoadLink.getId());
                videoDownloadLinkDTO.setVideoId(s);
                videoDownloadLinkDTO.setName(infoDownLoadLink.getName());
                videoDownloadLinkDTO.setUrl(infoDownLoadLink.getUrl());
                String id2 = infoDownLoadLink.getId();
                //对磁力链接id进行判断，如果是“ ”，则忽略
                if (id2==""){
                    break;
                }
                int j = Integer.parseInt(infoDownLoadLink.getId());
                if(j<0){
                    videoDownloadLinkDTO.setStatus(0);
                    videoDownloadLinkDTO.setOrderNo(0);
                    downloadLinkService.add(videoDownloadLinkDTO);
                }else{
                    downloadLinkService.update(videoDownloadLinkDTO);
                }

            }
        }

    }

    @Override
    public List<AppQueryResult> topList() {
        AppQueryParam queryParam = new AppQueryParam();
        queryParam.setIsTop(1);
        queryParam.setStartIdx(null);
        queryParam.setPage(null);
        try {
            return videoMapper.searchAppList(queryParam);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("小程序列表查询异常");
        }
        return Lists.emptyList();
    }

    @Override
    public List<AppQueryResult> appList(AppQueryParam param) {
        AppQueryParam queryParam = new AppQueryParam();
        queryParam.setStartIdx(PageUtil.computeStartIdx(param.getPage(), queryParam.getPageSize()));
        try {
            return videoMapper.searchAppList(queryParam);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("小程序列表查询异常");
        }
        return Lists.emptyList();
    }

    @Override
    public AppDetailInfo appDetail(String id) {
        try {
            // 1.查询视频详情及其关联图片的信息
            AppDetailInfo detailInfo = videoMapper.getAppDetail(id);
            if (Objects.isNull(detailInfo)) {
                LOGGER.error("未查询到视频id为：{}的信息", id);
                return null;
            }

            // 2.查询评论和下载链接的启用状态
            List<VideoSysCfg> cfgList = sysCfgMapper.list();
            if (CollectionUtils.isEmpty(cfgList)) {
                LOGGER.error("没有系统配置，请联系管理员进行检查配置");
                return detailInfo;
            }
            Map<String, Integer> cfgMap = cfgList.stream().collect(Collectors.toMap(VideoSysCfg::getKey, VideoSysCfg::getStatus));
            Integer offDownload = cfgMap.get("download");
            Integer offComment = cfgMap.get("comment");
            detailInfo.setOffLink(offDownload);
            detailInfo.setOffComment(offComment);

            // 3.根据启用状态判断是否需要查询评论和下载链接
            if (offComment == 1) {
                return detailInfo;
            }
            detailInfo.setCommentList(videoMapper.getCommentById(id));

            if (offDownload == 1) {
                return detailInfo;
            }
            detailInfo.setLinkList(videoMapper.getLinkById(id));
            return detailInfo;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("id为:{}的视频详情查询错误，请联系管理员。", id);
        }
        return null;
    }

    @Override
    public void AppAdd(AppAddInfo appAddInfo) throws SQLException {
        //获取视频id
        String videoId = appAddInfo.getVideoId();

        //将链接信息进行封装
        VideoDownloadLinkDTO videoDownloadLinkDTO = new VideoDownloadLinkDTO();
        videoDownloadLinkDTO.setName(appAddInfo.getLinkName());
        videoDownloadLinkDTO.setUrl(appAddInfo.getLinkUrl());
        videoDownloadLinkDTO.setOrderNo(0);
        videoDownloadLinkDTO.setStatus(0);

        //将视频信息封装
        VideoDetailDTO detailDTO = new VideoDetailDTO();
        detailDTO.setName(appAddInfo.getName());
        detailDTO.setCategory("");
        detailDTO.setType("");
        detailDTO.setRegion("");
        detailDTO.setYears(0);
        detailDTO.setStatus(0);
        detailDTO.setSummary("");
        //分类处理
        if (videoId == "") {
            //不存在该视频
            videoMapper.add(detailDTO);
            videoDownloadLinkDTO.setVideoId(detailDTO.getId().toString());
            downloadLinkService.add(videoDownloadLinkDTO);
        } else {
            videoDownloadLinkDTO.setVideoId(videoId);
            downloadLinkService.add(videoDownloadLinkDTO);
        }
    }

    @Override
    public void data() {
        try {
            // 从数据库中读取原来的数据信息
            List<OldData> oldDataList = videoMapper.getOldData();

            for (OldData oldData : oldDataList) {
                VideoDetailDTO detailDTO = new VideoDetailDTO();
                detailDTO.setName(oldData.getTitle());
                String categoryName = "";
                switch (oldData.getCategory()) {
                    case 1:
                        categoryName = "movie";
                        break;
                    case 6:
                        categoryName = "tv";
                        break;
                    case 7:
                        categoryName = "documentary";
                        break;
                    case 8:
                        categoryName = "concert";
                        break;
                    case 9:
                        categoryName = "tools";
                        break;
                    case 10:
                        categoryName = "4kbaike";
                        break;
                }
                detailDTO.setCategory(categoryName);
                detailDTO.setCreateTime(oldData.getDate());
                detailDTO.setStatus(1);
                detailDTO.setRegion("unknown");
                detailDTO.setType("type_unknown");
                detailDTO.setYears(1900);

                String strHtml = oldData.getContent();
                // 解析html中的图片信息
                List<InfoPicture> pics = new ArrayList<>();
                String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
                Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
                Matcher m_image = p_image.matcher(strHtml);
                int order = 0;
                while (m_image.find()) {
                    // 得到<img />数据
                    String img = m_image.group();
                    // 匹配<img>中的src数据
                    Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
                    while (m.find()) {
                        String url = m.group(1);
                        // 去除部分url中的空格及样式,及前缀拼接
                        int index = url.lastIndexOf(" ");
                        if (index != -1) {
                            url = url.substring(0, index);
                        }
                        url = url.substring(url.lastIndexOf("/") + 1);
                        InfoPicture picture = new InfoPicture();
                        picture.setUrl(url);
                        picture.setOrderNo(order++);
                        pics.add(picture);
                    }
                }
                detailDTO.setPictureList(pics);

                // 去除html标签和标题到空格符的文本数据及剩余的回车符和空格符
                String txt = strHtml.replaceAll("</?[^>]+>", "");
                txt = txt.replaceAll("\r\n", "");
                int index = txt.lastIndexOf("&nbsp;");
                if (index != -1) {
                    txt = txt.substring(index + 6);
                }
                detailDTO.setSummary(txt);

                Map<String, String> metaMap = new HashMap<>();
                for (Map<String, String> meta : oldData.getMeta()) {
                    metaMap.put(meta.get("key"), meta.get("value"));
                }
                List<InfoDownLoadLink> links = Lists.newArrayList();
                if (StringUtils.isNotBlank(metaMap.get("xzname1"))) {
                    InfoDownLoadLink link = new InfoDownLoadLink();
                    link.setName(metaMap.get("xzname1"));
                    link.setUrl(metaMap.get("xzurl1"));
                    link.setOrderNo(0);
                    links.add(link);
                }
                if (StringUtils.isNotBlank(metaMap.get("xzname2"))) {
                    InfoDownLoadLink link = new InfoDownLoadLink();
                    link.setName(metaMap.get("xzname2"));
                    link.setUrl(metaMap.get("xzurl2"));
                    link.setOrderNo(1);
                    links.add(link);
                }
                if (StringUtils.isNotBlank(metaMap.get("xzname3"))) {
                    InfoDownLoadLink link = new InfoDownLoadLink();
                    link.setName(metaMap.get("xzname3"));
                    link.setUrl(metaMap.get("xzurl3"));
                    link.setOrderNo(2);
                    links.add(link);
                }
                if (StringUtils.isNotBlank(metaMap.get("xzname4"))) {
                    InfoDownLoadLink link = new InfoDownLoadLink();
                    link.setName(metaMap.get("xzname4"));
                    link.setUrl(metaMap.get("xzurl4"));
                    link.setOrderNo(3);
                    links.add(link);
                }
                if (StringUtils.isNotBlank(metaMap.get("xzname5"))) {
                    InfoDownLoadLink link = new InfoDownLoadLink();
                    link.setName(metaMap.get("xzname5"));
                    link.setUrl(metaMap.get("xzurl5"));
                    link.setOrderNo(4);
                    links.add(link);
                }
                detailDTO.setLinkList(links);
                this.add(detailDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}