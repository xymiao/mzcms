-- CMS系统功能增强数据库表结构
-- 创建评论表
CREATE TABLE `cms_comment` (
  `comment_id` VARCHAR(32) NOT NULL COMMENT '评论ID',
  `content_id` VARCHAR(32) NOT NULL COMMENT '内容ID',
  `parent_id` VARCHAR(32) NULL COMMENT '父评论ID',
  `user_id` VARCHAR(32) NULL COMMENT '用户ID',
  `user_name` VARCHAR(64) NULL COMMENT '用户名',
  `user_email` VARCHAR(100) NULL COMMENT '用户邮箱',
  `user_website` VARCHAR(200) NULL COMMENT '用户网站',
  `comment_content` TEXT NOT NULL COMMENT '评论内容',
  `comment_status` VARCHAR(2) DEFAULT '0' COMMENT '评论状态 0-待审核 1-已通过 2-已拒绝',
  `ip_address` VARCHAR(50) NULL COMMENT 'IP地址',
  `user_agent` VARCHAR(500) NULL COMMENT '用户代理',
  `created` DATETIME NULL COMMENT '创建时间',
  `updated` DATETIME NULL COMMENT '更新时间',
  `del_flag` VARCHAR(2) DEFAULT '0' COMMENT '删除标志 0-正常 1-已删除',
  PRIMARY KEY (`comment_id`),
  INDEX `idx_content_id` (`content_id`),
  INDEX `idx_parent_id` (`parent_id`),
  INDEX `idx_status` (`comment_status`),
  INDEX `idx_created` (`created`)
) COMMENT = '评论表';

-- 创建标签表
CREATE TABLE `cms_tag` (
  `tag_id` VARCHAR(32) NOT NULL COMMENT '标签ID',
  `tag_name` VARCHAR(50) NOT NULL COMMENT '标签名称',
  `tag_slug` VARCHAR(50) NOT NULL COMMENT '标签别名',
  `tag_desc` VARCHAR(200) NULL COMMENT '标签描述',
  `tag_color` VARCHAR(10) NULL COMMENT '标签颜色',
  `use_count` INT DEFAULT 0 COMMENT '使用次数',
  `created` DATETIME NULL COMMENT '创建时间',
  `updated` DATETIME NULL COMMENT '更新时间',
  `user_id` VARCHAR(32) NULL COMMENT '创建用户ID',
  `del_flag` VARCHAR(2) DEFAULT '0' COMMENT '删除标志 0-正常 1-已删除',
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `uk_tag_name` (`tag_name`),
  UNIQUE KEY `uk_tag_slug` (`tag_slug`),
  INDEX `idx_use_count` (`use_count`)
) COMMENT = '标签表';

-- 创建内容标签关联表
CREATE TABLE `cms_content_tag` (
  `ct_id` VARCHAR(32) NOT NULL COMMENT '关联ID',
  `content_id` VARCHAR(32) NOT NULL COMMENT '内容ID',  
  `tag_id` VARCHAR(32) NOT NULL COMMENT '标签ID',
  `created` DATETIME NULL COMMENT '创建时间',
  `creater` VARCHAR(32) NULL COMMENT '创建人',
  PRIMARY KEY (`ct_id`),
  UNIQUE KEY `uk_content_tag` (`content_id`, `tag_id`),
  INDEX `idx_content_id` (`content_id`),
  INDEX `idx_tag_id` (`tag_id`)
) COMMENT = '内容标签关联表';

-- 创建媒体文件表
CREATE TABLE `cms_media` (
  `media_id` VARCHAR(32) NOT NULL COMMENT '媒体ID',
  `file_name` VARCHAR(200) NOT NULL COMMENT '文件名',
  `original_name` VARCHAR(200) NOT NULL COMMENT '原始文件名',
  `file_path` VARCHAR(500) NOT NULL COMMENT '文件路径',
  `file_url` VARCHAR(500) NOT NULL COMMENT '文件URL',
  `file_size` BIGINT NULL COMMENT '文件大小',
  `file_type` VARCHAR(20) NULL COMMENT '文件类型',
  `mime_type` VARCHAR(100) NULL COMMENT 'MIME类型',
  `media_type` VARCHAR(20) NULL COMMENT '媒体类型 image/video/audio/document',
  `alt_text` VARCHAR(200) NULL COMMENT '替代文本',
  `title` VARCHAR(200) NULL COMMENT '标题',
  `description` TEXT NULL COMMENT '描述',
  `created` DATETIME NULL COMMENT '创建时间',
  `updated` DATETIME NULL COMMENT '更新时间',
  `user_id` VARCHAR(32) NULL COMMENT '上传用户ID',
  `del_flag` VARCHAR(2) DEFAULT '0' COMMENT '删除标志 0-正常 1-已删除',
  PRIMARY KEY (`media_id`),
  INDEX `idx_media_type` (`media_type`),
  INDEX `idx_file_type` (`file_type`),
  INDEX `idx_user_id` (`user_id`),
  INDEX `idx_created` (`created`)
) COMMENT = '媒体文件表';

-- 创建友情链接表
CREATE TABLE `cms_link` (
  `link_id` VARCHAR(32) NOT NULL COMMENT '链接ID',
  `link_name` VARCHAR(100) NOT NULL COMMENT '链接名称',
  `link_url` VARCHAR(500) NOT NULL COMMENT '链接地址',
  `link_desc` VARCHAR(200) NULL COMMENT '链接描述',
  `link_logo` VARCHAR(500) NULL COMMENT '链接Logo',
  `link_type` VARCHAR(20) DEFAULT 'friend' COMMENT '链接类型 friend/partner/sponsor',
  `link_status` VARCHAR(2) DEFAULT '0' COMMENT '链接状态 0-待审核 1-已通过 2-已拒绝',
  `sort_order` INT DEFAULT 0 COMMENT '排序',
  `contact_email` VARCHAR(100) NULL COMMENT '联系邮箱',
  `contact_name` VARCHAR(50) NULL COMMENT '联系人',
  `created` DATETIME NULL COMMENT '创建时间',
  `updated` DATETIME NULL COMMENT '更新时间',
  `user_id` VARCHAR(32) NULL COMMENT '创建用户ID',
  `del_flag` VARCHAR(2) DEFAULT '0' COMMENT '删除标志 0-正常 1-已删除',
  PRIMARY KEY (`link_id`),
  INDEX `idx_link_type` (`link_type`),
  INDEX `idx_link_status` (`link_status`),
  INDEX `idx_sort_order` (`sort_order`)
) COMMENT = '友情链接表';

-- 创建SEO信息表
CREATE TABLE `cms_seo` (
  `seo_id` VARCHAR(32) NOT NULL COMMENT 'SEO ID',
  `content_id` VARCHAR(32) NOT NULL COMMENT '内容ID',
  `content_type` VARCHAR(20) NOT NULL COMMENT '内容类型 content/category/page',
  `meta_title` VARCHAR(200) NULL COMMENT 'Meta标题',
  `meta_description` VARCHAR(500) NULL COMMENT 'Meta描述',
  `meta_keywords` VARCHAR(300) NULL COMMENT 'Meta关键词',
  `canonical_url` VARCHAR(500) NULL COMMENT '规范URL',
  `og_title` VARCHAR(200) NULL COMMENT 'Open Graph标题',
  `og_description` VARCHAR(500) NULL COMMENT 'Open Graph描述',
  `og_image` VARCHAR(500) NULL COMMENT 'Open Graph图片',
  `og_type` VARCHAR(50) NULL COMMENT 'Open Graph类型',
  `twitter_card` VARCHAR(50) NULL COMMENT 'Twitter Card类型',
  `schema_markup` TEXT NULL COMMENT 'Schema结构化数据',
  `created` DATETIME NULL COMMENT '创建时间',
  `updated` DATETIME NULL COMMENT '更新时间',
  `user_id` VARCHAR(32) NULL COMMENT '创建用户ID',
  PRIMARY KEY (`seo_id`),
  UNIQUE KEY `uk_content_type` (`content_id`, `content_type`),
  INDEX `idx_content_id` (`content_id`),
  INDEX `idx_content_type` (`content_type`)
) COMMENT = 'SEO信息表';

-- 为现有cms_content表添加缺失的字段
ALTER TABLE `cms_content` ADD COLUMN `user_id` VARCHAR(32) NULL COMMENT '创建人' AFTER `publish_user`;