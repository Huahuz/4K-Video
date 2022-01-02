use 4k_video;

-- 视频表
drop table if exists `video`;
create table if not exists `video` (
	`id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT not null COMMENT "视频记录id",
	`name` varchar(100) not null COMMENT "视频名称",
	`category` varchar(20) not null COMMENT "视频类别，电影、电视剧、纪录片、演唱会、4k工具、其它",
	`type` varchar(500) not null comment "视频类型，多个类型由逗号进行分割",
	`region` varchar(20) not null comment "地区，如中国、欧美、日本、韩国、其它",
	`years` int not null comment "年代，此视频的年代",
	`status` int not null comment "状态，未发布、已发布、已冻结",
	`is_top` int not null default 0 comment "是否置顶，0否 1是",
	`summary` LONGTEXT COLLATE utf8mb4_unicode_520_ci NOT NULL comment "简要概述",
	`create_time` datetime DEFAULT now() COMMENT "创建时间",
	`update_time` datetime default now() comment "更新时间",
	`comments` VARCHAR(500) COMMENT "备注说明"
) engine=InnoDB;

-- 视频图片表
drop table if exists `video_picture`;
create table if not exists `video_picture` (
	`id` bigint(20) PRIMARY KEY AUTO_INCREMENT not null comment "图片id",
	`video_id` bigint(20) not null comment "关联的视频id",
	`name` varchar(100) not null comment "图片名称",
	`url` varchar(200) not null comment "图片地址",
	`thumbnails_url` varchar(200) comment "略缩图地址",
	`order_no` int default 0 comment "同一视频图片顺序",
	`create_time` datetime not null DEFAULT now() COMMENT "创建时间",
	`update_time` datetime not null default now() comment "更新时间"
) engine=InnoDB;

-- 视频下载链接表
drop table if exists `video_download_link`;
create table if not exists `video_download_link` (
	`id` bigint(20) PRIMARY KEY AUTO_INCREMENT not null comment "下载链接id",
	`video_id` bigint(20) not null comment "关联的视频id",
	`name` varchar(100) not null comment "链接名称",
	`url` varchar(200) not null comment "链接地址",
	`order_no` int default 0 comment "同一视频链接顺序",
	`status` int not null default 0 comment "链接状态，未发布、已发布、已冻结"
) engine=InnoDB;

-- 用户评论表
drop table if exists `video_comment`;
create table if not exists `video_comment` (
	`id` bigint(20) PRIMARY KEY AUTO_INCREMENT not null comment "评论id",
	`video_id` bigint(20) not null comment "关联的视频id",
	`user_name` varchar(100) not null comment "用户名",
	`content` varchar(200) comment "评论内容",
	`score` int not null default 5 comment "评分",
	`comment_time` datetime not null default now() comment "评论时间",
	`is_complaint` int not null default 0 comment "是否为投诉建议0否，1是",
	`status` int not null default 0 comment "链接状态，未发布、已发布、已受理"
) engine=InnoDB;

-- 视频过滤配置表
drop table if exists `video_filter_cfg`;
create table if not exists `video_filter_cfg` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT not null comment "类型id",
    `parent_id` bigint(20) comment "父类型id",
    `key` varchar(50) not null comment "类型键",
    `value` varchar(50) not null comment "类型值",
    `order_no` int not null default 0 comment "类型顺序，默认为0，0-n升序排列",
    `type` int comment "配置项类型，0为类型项配置 1为类别项配置 2为地区项配置"
) engine=InnoDB;

-- 系统配置表
drop table if exists `video_sys_cfg`;
create table if not exists `video_sys_cfg` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT not null comment "系统配置id",
    `key` varchar(50) not null comment "系统配置键",
    `value` varchar(50) not null comment "系统配置值",
	`status` int not null default 0 comment "配置状态0启用 1禁用",
    `order_no` int not null default 0 comment "配置顺序，默认为0，0-n升序排列"
) engine=InnoDB;