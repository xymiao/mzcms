package com.xymiao.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsMedia;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CmsMediaService {
    
    /**
     * 上传文件
     */
    CmsMedia uploadFile(MultipartFile file, String userId);
    
    /**
     * 保存媒体信息
     */
    CmsMedia saveMedia(CmsMedia media);
    
    /**
     * 更新媒体信息
     */
    int updateMedia(CmsMedia media);
    
    /**
     * 删除媒体文件
     */
    int deleteMedia(String mediaId);
    
    /**
     * 根据ID获取媒体信息
     */
    CmsMedia getMedia(String mediaId);
    
    /**
     * 分页查询媒体文件
     */
    Page<CmsMedia> queryMediaList(Integer current, Integer size, String mediaType);
    
    /**
     * 根据用户ID查询媒体文件
     */
    List<CmsMedia> getMediaByUserId(String userId);
    
    /**
     * 根据文件类型查询媒体文件
     */
    List<CmsMedia> getMediaByFileType(String fileType);
}