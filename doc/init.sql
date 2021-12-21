use 4k_video;

-- 视频表
create table if not exists `video` (
	`id` BIGINT(20) PRIMARY KEY not null COMMENT "视频记录id",
	`name` varchar(100) not null COMMENT "视频名称",
	`category` int not null COMMENT "视频类别，电影、电视剧、纪录片、演唱会、4k工具、其它",
	`type` varchar(500) not null comment "视频类型，多个类型由逗号进行分割",
	`region` int not null comment "地区，如中国、欧美、日本、韩国、其它",
	`years` int not null comment "年代，此视频的年代",
	`status` int not null comment "状态，未发布、已发布、已冻结",
	`summary` LONGTEXT COLLATE utf8mb4_unicode_520_ci NOT NULL comment "简要概述",
	`create_time` datetime not null DEFAULT now() COMMENT "创建时间",
	`update_time` datetime not null default now() comment "更新时间",
	`comments` VARCHAR(500) COMMENT "备注说明"
) engine=InnoDB;

-- 视频图片表
create table if not exists `video_picture` (
	`id` bigint(20) PRIMARY KEY AUTO_INCREMENT not null comment "图片id",
	`video_id` bigint(20) not null comment "关联的视频id",
	`name` varchar(100) not null comment "图片名称",
	`url` varchar(200) not null comment "图片地址",
	`thumbnails_url` varchar(200) not null comment "略缩图地址",
	`order_no` int not null default 0 comment "同一视频图片顺序",
	`create_time` datetime not null DEFAULT now() COMMENT "创建时间",
	`update_time` datetime not null default now() comment "更新时间"
) engine=InnoDB;

-- 视频下载链接表
create table if not exists `video_download_link` (
	`id` bigint(20) PRIMARY KEY AUTO_INCREMENT not null comment "下载链接id",
	`video_id` bigint(20) not null comment "关联的视频id",
	`name` varchar(100) not null comment "链接名称",
	`url` varchar(200) not null comment "链接地址",
	`order_no` int not null default 0 comment "同一视频链接顺序",
	`status` int not null default 0 comment "链接状态，未发布、已发布、已冻结"
) engine=InnoDB;

-- 用户评论表
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
