package com.xymiao.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.mapper.CmsMediaMapper;
import com.xymiao.cms.pojo.CmsMedia;
import com.xymiao.common.exception.BusinessException;
import com.xymiao.common.utils.IdsUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service("cmsMediaServiceImpl")
public class CmsMediaServiceImpl implements CmsMediaService {
    
    private final CmsMediaMapper cmsMediaMapper;
    
    @Value("${cms.upload.path:/upload}")
    private String uploadPath;
    
    @Value("${cms.upload.url:/upload}")
    private String uploadUrl;
    
    public CmsMediaServiceImpl(CmsMediaMapper cmsMediaMapper) {
        this.cmsMediaMapper = cmsMediaMapper;
    }
    
    @Override
    public CmsMedia uploadFile(MultipartFile file, String userId) {
        if (file.isEmpty()) {
            throw new BusinessException("文件不能为空！");
        }
        
        try {
            // 生成文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + extension;
            
            // 按日期创建目录
            String dateDir = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String dirPath = uploadPath + File.separator + dateDir;
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // 保存文件
            String filePath = dirPath + File.separator + fileName;
            file.transferTo(new File(filePath));
            
            // 保存媒体信息
            CmsMedia media = new CmsMedia();
            media.setFileName(fileName);
            media.setOriginalName(originalFilename);
            media.setFilePath(filePath);
            media.setFileUrl(uploadUrl + "/" + dateDir + "/" + fileName);
            media.setFileSize(file.getSize());
            media.setMimeType(file.getContentType());
            media.setFileType(extension.substring(1));
            media.setUserId(userId);
            
            // 判断媒体类型
            String contentType = file.getContentType();
            if (contentType.startsWith("image/")) {
                media.setMediaType("image");
            } else if (contentType.startsWith("video/")) {
                media.setMediaType("video");
            } else if (contentType.startsWith("audio/")) {
                media.setMediaType("audio");
            } else {
                media.setMediaType("document");
            }
            
            return saveMedia(media);
            
        } catch (IOException e) {
            throw new BusinessException("文件上传失败：" + e.getMessage());
        }
    }
    
    @Override
    public CmsMedia saveMedia(CmsMedia media) {
        LocalDateTime currDate = LocalDateTime.now();
        
        media.setMediaId(IdsUtils.getId());
        media.setCreated(currDate);
        media.setDelFlag("0");
        
        int rows = cmsMediaMapper.insert(media);
        if (rows <= 0) {
            throw new BusinessException("媒体信息保存失败！");
        }
        return media;
    }
    
    @Override
    public int updateMedia(CmsMedia media) {
        media.setUpdated(LocalDateTime.now());
        return cmsMediaMapper.updateById(media);
    }
    
    @Override
    public int deleteMedia(String mediaId) {
        CmsMedia media = new CmsMedia();
        media.setMediaId(mediaId);
        media.setDelFlag("1");
        return cmsMediaMapper.updateById(media);
    }
    
    @Override
    public CmsMedia getMedia(String mediaId) {
        return cmsMediaMapper.selectById(mediaId);
    }
    
    @Override
    public Page<CmsMedia> queryMediaList(Integer current, Integer size, String mediaType) {
        Page<CmsMedia> page = new Page<>(current, size);
        if (mediaType != null && !mediaType.isEmpty()) {
            return cmsMediaMapper.selectByMediaType(page, mediaType);
        } else {
            return cmsMediaMapper.selectPage(page, null);
        }
    }
    
    @Override
    public List<CmsMedia> getMediaByUserId(String userId) {
        return cmsMediaMapper.selectByUserId(userId);
    }
    
    @Override
    public List<CmsMedia> getMediaByFileType(String fileType) {
        return cmsMediaMapper.selectByFileType(fileType);
    }
}