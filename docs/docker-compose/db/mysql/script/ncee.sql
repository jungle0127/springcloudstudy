--
-- Initial database
--
DROP DATABASE IF EXISTS `ncee`;
CREATE DATABASE ncee DEFAULT CHARACTER SET utf8;
USE ncee;

--
-- Social user table from Spring social
--
create table UserConnection (userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(512) not null,
	secret varchar(512),
	refreshToken varchar(512),
	expireTime bigint,
	primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
 --
 -- remember-me token from JdbcTokenRepositoryImpl
 --
create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null);
-- -------------------------------------- User tables --------------------------------------
--
-- users table
--
/*
CREATE TABLE users(
  username VARCHAR(64) not null,
  password varchar(64) not null,
  enable BOOLEAN NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- authorities table
--
CREATE TABLE authorities(
  username VARCHAR(64) not null,
  authority varchar(64) not null,
  CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES ncee.users(username)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX  ix_auth_username ON authorities (username,authority);
-- -------------------------------------- group tables --------------------------------------

CREATE TABLE  groups (
  id BIGINT AUTO_INCREMENT primary key,
  group_name VARCHAR(64) not null
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

create table group_authorities (
  group_id bigint not null,
  authority varchar(64) not null,
  constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
  id bigint AUTO_INCREMENT primary key,
  username varchar(50) not null,
  group_id bigint not null,
  constraint fk_group_members_group foreign key(group_id) references groups(id)
);
*/
-- -------------------------------------------------------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
`id` bigint(20) auto_increment,
`role_name` varchar(40) NOT NULL,
`active` int DEFAULT 1,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user_role(role_name) values('admin');
INSERT INTO user_role(role_name) VALUES('teacher');

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
`id` bigint(20) auto_increment,
`username` varchar(40) NOT NULL UNIQUE,
`password` varchar(60) NOT NULL,
`phone_number` char(30) NOT NULL UNIQUE,
`roleid` bigint(20) NOT NULL,
`active` int DEFAULT 1,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE users ADD CONSTRAINT FK_user_role FOREIGN KEY (roleid) REFERENCES user_role(id);
INSERT INTO users(username,password,phone_number,roleid) VALUES ('admin','$2a$10$yqoq0rusGCdo0wfXCi3CKetQN8ayJUlFeXySxrIy5QdutrfdWmhNm','18088888888',1);
-- usr: ps pwd: lotus
INSERT INTO users(username,password,phone_number,roleid) VALUES ('ps','$2a$10$yqoq0rusGCdo0wfXCi3CKetQN8ayJUlFeXySxrIy5QdutrfdWmhNm','18088888888',2);

DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights` (
	`id` bigint(20) auto_increment,
	`right_holder_id` bigint(20) NOT NULL,
	`right_holer_type` bigint(20) NOT NULL, -- 0: group 1: Individual user
	`right_url` varchar(200) NOT NULL,
	`active` int DEFAULT 1,
	PRIMARY KEY(`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;


--
-- table province for each question
--
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
`id` bigint(20) auto_increment ,
`province` varchar(25) NOT NULL ,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table province
--

INSERT INTO province(province) VALUES('新课标一');
INSERT INTO province(province) VALUES('新课标二');
INSERT INTO province(province) VALUES('大纲卷');
INSERT INTO province(province) VALUES('北京卷');
INSERT INTO province(province) VALUES('天津卷');
INSERT INTO province(province) VALUES('上海卷');
INSERT INTO province(province) VALUES('广东卷');
INSERT INTO province(province) VALUES('四川卷');
INSERT INTO province(province) VALUES('江苏卷');
INSERT INTO province(province) VALUES('安徽卷');
INSERT INTO province(province) VALUES('浙江卷');
INSERT INTO province(province) VALUES('福建卷');
INSERT INTO province(province) VALUES('辽宁卷');
INSERT INTO province(province) VALUES('湖南卷');
INSERT INTO province(province) VALUES('湖北卷');
INSERT INTO province(province) VALUES('江西卷');
INSERT INTO province(province) VALUES('陕西卷');
INSERT INTO province(province) VALUES('山东卷');
INSERT INTO province(province) VALUES('重庆卷');
INSERT INTO province(province) VALUES('海南卷');


--
-- Table check type考察类型
--
DROP TABLE IF EXISTS `check_type`;
CREATE TABLE `check_type` (
`id` bigint(20) auto_increment ,
`type` varchar(20) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table check_type
--

INSERT INTO check_type(type) VALUES ('概念');
INSERT INTO check_type(type) VALUES ('公式');
INSERT INTO check_type(type) VALUES ('实验');
INSERT INTO check_type(type) VALUES ('技巧');

--
-- Table construct type 组合类型
--
DROP TABLE IF EXISTS `construct_type`;
CREATE TABLE `construct_type` (
`id` bigint(20) auto_increment ,
`type` varchar(20) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table check_type
--

INSERT INTO construct_type(type) VALUES ('单科');
INSERT INTO construct_type(type) VALUES ('物理生物');
INSERT INTO construct_type(type) VALUES ('物理化学');
INSERT INTO construct_type(type) VALUES ('生物化学');
INSERT INTO construct_type(type) VALUES ('历史政治');
INSERT INTO construct_type(type) VALUES ('历史地理');
INSERT INTO construct_type(type) VALUES ('政治地理');


--
-- Table score
--

DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
`id` bigint(20) auto_increment ,
`score` int NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table score
--

INSERT INTO score(score) VALUES(6);
INSERT INTO score(score) VALUES(2);
INSERT INTO score(score) VALUES(3);
INSERT INTO score(score) VALUES(15);
INSERT INTO score(score) VALUES(17);
INSERT INTO score(score) VALUES(19);

--
-- Table year
--

DROP TABLE IF EXISTS `year`;
CREATE TABLE `year` (
`id` bigint(20) auto_increment ,
`year` varchar(8) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table year
--

INSERT INTO year(year) VALUES('2005');
INSERT INTO year(year) VALUES('2006');
INSERT INTO year(year) VALUES('2007');
INSERT INTO year(year) VALUES('2008');
INSERT INTO year(year) VALUES('2009');
INSERT INTO year(year) VALUES('2010');
INSERT INTO year(year) VALUES('2011');
INSERT INTO year(year) VALUES('2012');
INSERT INTO year(year) VALUES('2013');
INSERT INTO year(year) VALUES('2014');
INSERT INTO year(year) VALUES('2015');
INSERT INTO year(year) VALUES('2016');
INSERT INTO year(year) VALUES('2017');
INSERT INTO year(year) VALUES('2018');

--
-- Table difficulty_type 
--
DROP TABLE IF EXISTS `difficulty_type`;
CREATE TABLE `difficulty_type` (
`id` bigint(20) auto_increment ,
`type` varchar(20) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table difficulty_type
--

INSERT INTO difficulty_type(type) VALUES ('易');
INSERT INTO difficulty_type(type) VALUES ('中');
INSERT INTO difficulty_type(type) VALUES ('难');

--
-- create table for examination type
--
DROP TABLE IF EXISTS `exam_type`;
CREATE TABLE `exam_type`(
	`id` bigint(20) auto_increment,
	`type` varchar(50) NOT NULL,
	`active` int DEFAULT 1 ,
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Initiate the table exam_type
--
INSERT INTO exam_type(type) VALUES ('高考');
INSERT INTO exam_type(type) VALUES ('教师资格考试');
--
-- Table course
--
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
`id` bigint(20) auto_increment,
`exam_type_id` bigint NOT NULL,
`course` varchar(20) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table course
--
INSERT INTO course(exam_type_id,course) VALUES (1,'语文');
INSERT INTO course(exam_type_id,course) VALUES (1,'数学（理）');
INSERT INTO course(exam_type_id,course) VALUES (1,'数学（文）');
INSERT INTO course(exam_type_id,course) VALUES (1,'英语');
INSERT INTO course(exam_type_id,course) VALUES (1,'文综');
INSERT INTO course(exam_type_id,course) VALUES (1,'理综');
INSERT INTO course(exam_type_id,course) VALUES (1,'化学');
INSERT INTO course(exam_type_id,course) VALUES (1,'物理');
INSERT INTO course(exam_type_id,course) VALUES (1,'生物');
INSERT INTO course(exam_type_id,course) VALUES (1,'历史');
INSERT INTO course(exam_type_id,course) VALUES (1,'政治');
INSERT INTO course(exam_type_id,course) VALUES (1,'地理');
INSERT INTO course(exam_type_id,course) VALUES (1,'基础能力');
INSERT INTO course(exam_type_id,course) VALUES (2,'教育学');
INSERT INTO course(exam_type_id,course) VALUES (2,'教育心理学');

--
-- table book
--
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
`id` bigint(20) auto_increment ,
`course_id` bigint(20) NOT NULL,
`book` varchar(30) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`),
FOREIGN KEY(course_id) REFERENCES course(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table book
--
INSERT INTO book(course_id,book) VALUES (8,'必修1'); -- 1
INSERT INTO book(course_id,book) VALUES (8,'必修2'); -- 2
INSERT INTO book(course_id,book) VALUES (8,'选修3-1'); -- 3
INSERT INTO book(course_id,book) VALUES (8,'选修3-2'); -- 4
INSERT INTO book(course_id,book) VALUES (8,'选修3-3'); -- 5
INSERT INTO book(course_id,book) VALUES (8,'选修3-4'); -- 6
INSERT INTO book(course_id,book) VALUES (8,'选修3-5'); -- 7
INSERT INTO book(course_id,book) VALUES (14,'教育学'); -- 8
INSERT INTO book(course_id,book) VALUES (15,'教育心理学');-- 9
INSERT INTO book(course_id,book) VALUES (8,'选修1-1'); -- 10
INSERT INTO book(course_id,book) VALUES (8,'选修1-2'); -- 11
INSERT INTO book(course_id,book) VALUES (8,'选修2-1'); -- 12
INSERT INTO book(course_id,book) VALUES (8,'选修2-2'); -- 13
INSERT INTO book(course_id,book) VALUES (8,'选修2-3'); -- 14
 
DROP TABLE IF EXISTS `knowledge_level`;
CREATE TABLE `knowledge_level` (
	`id` bigint(20) auto_increment,
	`level` varchar(10) NOT NULL,
	`description` varchar(500),
	`active` int DEFAULT 1,
	PRIMARY KEY(`id`)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- initiate table knowledge_level
--
INSERT INTO knowledge_level(level,description) VALUES ('Ⅰ','对所列知识要知道其内容及含义，并能在有关问题中识别和直接使用。');
INSERT INTO knowledge_level(level,description) VALUES ('Ⅱ','对所列知识要理解其确切含义及与其他知识的联系，能够进行叙述和解释，并能在实际问题的分析、综合、推理和判断等过程中运用。');

--
-- Table knowledge module
--
DROP TABLE IF EXISTS `knowledge_module`;
CREATE TABLE `knowledge_module` (
	`id` bigint(20) auto_increment,
	`book_id` bigint(20) NOT NULL,
	`modulename` varchar(100) NOT NULL,
	`note` varchar(500),
	`active` int DEFAULT 1,
	PRIMARY KEY(`id`),
	FOREIGN KEY(book_id) REFERENCES book(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Initiate knowledge module table.
--

INSERT INTO knowledge_module(book_id,modulename) VALUES (1,'质点的直线运动'); -- 1
INSERT INTO knowledge_module(book_id,modulename) VALUES (1,'相互作用与牛顿运动规律');-- 2
INSERT INTO knowledge_module(book_id,modulename) VALUES (2,'机械能'); -- 3
INSERT INTO knowledge_module(book_id,modulename) VALUES (2,'抛体运动与圆周运动'); -- 4
INSERT INTO knowledge_module(book_id,modulename) VALUES (2,'万有引力定律'); -- 5
INSERT INTO knowledge_module(book_id,modulename) VALUES (3,'电场'); -- 6
INSERT INTO knowledge_module(book_id,modulename) VALUES (3,'电路'); -- 7
INSERT INTO knowledge_module(book_id,modulename) VALUES (3,'磁场'); -- 8
INSERT INTO knowledge_module(book_id,modulename) VALUES (4,'电磁感应'); -- 9
INSERT INTO knowledge_module(book_id,modulename) VALUES (4,'交变电流'); -- 10
INSERT INTO knowledge_module(book_id,modulename) VALUES (6,'机械振动与机械波'); -- 11
INSERT INTO knowledge_module(book_id,modulename) VALUES (6,'电磁振荡与电磁波'); -- 12
INSERT INTO knowledge_module(book_id,modulename) VALUES (6,'光'); -- 13
INSERT INTO knowledge_module(book_id,modulename) VALUES (6,'相对论'); -- 14


--
-- Table knowledge
--
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
`id` bigint(20) auto_increment ,
`module_id` bigint(20) NOT NULL,
`knowledge` varchar(50) NOT NULL,
`description` varchar(1000),
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`),
FOREIGN KEY(module_id) REFERENCES knowledge_module(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- DROP INDEX IF EXISTS `IX_knowledge_chapter_id`;
-- CREATE INDEX IX_knowledge_chapter_id ON knowledge(chapter_id);

--
-- Init table knowledge
--
INSERT INTO knowledge (module_id,knowledge) VALUES (1,'参考系、质点');
INSERT INTO knowledge (module_id,knowledge) VALUES (1,'位移、速度和加速度');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (1,'匀变速直线运动及其公式、图像','匀变速直线运动图像只限于v-t 图像');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (2,'滑动摩擦力、动摩擦因数、静摩擦力','处理物体在粗糙面上的问题，只限于已知相对运动趋势或已知运动方向的情况');
INSERT INTO knowledge (module_id,knowledge) VALUES (2,'形变、弹性、胡克定律');
INSERT INTO knowledge (module_id,knowledge) VALUES (2,'矢量和标量');
INSERT INTO knowledge (module_id,knowledge) VALUES (2,'力的合成与分解');
INSERT INTO knowledge (module_id,knowledge) VALUES (2,'共点力的平衡');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (2,'牛顿运动定律、牛顿运动定律的应用','处理物体在粗糙面上的问题，只限于已知相对运动趋势或已知运动方向的情况');       
INSERT INTO knowledge (module_id,knowledge) VALUES (2,'超重和失重');
INSERT INTO knowledge (module_id,knowledge) VALUES (3,'功和功率');
INSERT INTO knowledge (module_id,knowledge) VALUES (3,'动能 动能定理');
INSERT INTO knowledge (module_id,knowledge) VALUES (3,'重力做功与重力势能');
INSERT INTO knowledge (module_id,knowledge) VALUES (3,'功能关系、机械能守恒定律及其应用');
INSERT INTO knowledge (module_id,knowledge) VALUES (4,'运动的合成与分解');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (4,'抛体运动','斜抛运动只作定性分析');
INSERT INTO knowledge (module_id,knowledge) VALUES (4,'匀速圆周运动、角速度、线速度、向心力加速度');
INSERT INTO knowledge (module_id,knowledge) VALUES (4,'匀速圆周运动的向心力');
INSERT INTO knowledge (module_id,knowledge) VALUES (4,'离心现象');
INSERT INTO knowledge (module_id,knowledge) VALUES (5,'万有引力定律及其应用');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (5,'环绕速度','包括计算第一宇宙速度');
INSERT INTO knowledge (module_id,knowledge) VALUES (5,'第二宇宙速度和第三宇宙速度');
INSERT INTO knowledge (module_id,knowledge) VALUES (5,'经典时空观和相对论时空观');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'物质的电结构、电荷守恒');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'静电现象的解释');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'点电荷');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'库仑定律');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'静电场');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'电场强度、点电荷的场强');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'电场线');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'电势能、电势');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'电势差');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'匀强电场中电势差和电场强度的关系');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (6,'带电粒子在匀强电场中运动','带电粒子在匀强电场中运动的计算，只限于带点粒子进入电场时速度平行或垂直于场强方向的情况');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'示波管');
INSERT INTO knowledge (module_id,knowledge) VALUES (6,'常见电容器、电容器的电压、电荷量和电容的关系');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (7,'欧姆定律','不要求解反电动势的问题');
INSERT INTO knowledge (module_id,knowledge) VALUES (7,'电阻定律');
INSERT INTO knowledge (module_id,knowledge) VALUES (7,'电阻的串联、并联');
INSERT INTO knowledge (module_id,knowledge) VALUES (7,'电源的电动势和内阻');-- 40
INSERT INTO knowledge (module_id,knowledge) VALUES (7,'闭合电路欧姆定律');
INSERT INTO knowledge (module_id,knowledge) VALUES (7,'电功率、焦耳定律');
INSERT INTO knowledge (module_id,knowledge) VALUES (8,'磁场、磁感应强度、磁感线 ');
INSERT INTO knowledge (module_id,knowledge) VALUES (8,'通电直导线和通电线圈周围磁场的方向');
INSERT INTO knowledge (module_id,knowledge) VALUES (8,'安培力、安培力的方向');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (8,'匀强磁场中的安培力','安培力的计算限于电流与匀强磁场方向垂直的情况');
INSERT INTO knowledge (module_id,knowledge) VALUES (8,'洛伦兹力、洛伦兹力的方向');
INSERT INTO knowledge (module_id,knowledge) VALUES (8,'洛伦兹力公式');
INSERT INTO knowledge (module_id,knowledge,description) VALUES (8,'带电粒子在匀强磁场中运动','洛伦兹力计算限于速度和磁感应强度垂直的情况');
INSERT INTO knowledge (module_id,knowledge) VALUES (8,'质谱仪和回旋加速器');
INSERT INTO knowledge (module_id,knowledge) VALUES(9,'电磁感应现象');
INSERT INTO knowledge (module_id,knowledge) VALUES(9,'磁通量');
INSERT INTO knowledge (module_id,knowledge,description) VALUES(9,'法拉第电磁感应定律','在电磁感应现象里，不要求判断内电路中各点电势的高低');
INSERT INTO knowledge (module_id,knowledge,description) VALUES(9,'楞次定律','导体切割磁感线时，感应电动势的计算，只限于l垂直于B,V的情况');
INSERT INTO knowledge (module_id,knowledge,description) VALUES(9,'自感 涡流','不要求用自感系数计算自感电动势');
INSERT INTO knowledge (module_id,knowledge,description) VALUES(10,'交变电流、交变电流的图像','不要求讨论交变电流的相位和相位差的问题');
INSERT INTO knowledge (module_id,knowledge,description) VALUES(10,'正弦交变电流的函数表达式、峰值和有效值','不要求讨论交变电流的相位和相位差的问题');
INSERT INTO knowledge (module_id,knowledge,description) VALUES(10,'理想变压器','只限于单相理想变压器');
INSERT INTO knowledge (module_id,knowledge) VALUES(10,'远距离输电');
INSERT INTO knowledge (module_id,knowledge,description) VALUES(11,'简谐运动','简谐运动只限于单摆和弹簧振子');
INSERT INTO knowledge (module_id,knowledge,description) VALUES(11,'简谐运动的公式和图像','简谐运动的公式只限于回复力公式;图像只限于位移–时间图像');
INSERT INTO knowledge (module_id,knowledge) VALUES(11,'单摆、单摆的周期公式');
INSERT INTO knowledge (module_id,knowledge) VALUES(11,'受迫振动和共振');
INSERT INTO knowledge (module_id,knowledge) VALUES(11,'机械波');
INSERT INTO knowledge (module_id,knowledge) VALUES(11,'横波和纵波');
INSERT INTO knowledge (module_id,knowledge) VALUES(11,'横波的图像');
INSERT INTO knowledge (module_id,knowledge) VALUES(11,'波长、波速和频率（周期）的关系');
INSERT INTO knowledge (module_id,knowledge) VALUES(11,'波的干涉和衍射现象');
INSERT INTO knowledge (module_id,knowledge) VALUES(11,'多普勒效应');

INSERT INTO knowledge (module_id,knowledge) VALUES(12,'变化的磁场产生电场、变化的电场产生磁场、电磁波及其传播');
INSERT INTO knowledge (module_id,knowledge) VALUES(12,'电磁波的产生、发射和接收');
INSERT INTO knowledge (module_id,knowledge) VALUES(12,'电磁波谱');

INSERT INTO knowledge (module_id,knowledge) VALUES(13,'光的折射定律');
INSERT INTO knowledge (module_id,knowledge) VALUES(13,'折射率');
INSERT INTO knowledge (module_id,knowledge) VALUES(13,'全反射、光导纤维');
INSERT INTO knowledge (module_id,knowledge) VALUES(13,'光的干涉、衍射和偏振现象');

INSERT INTO knowledge (module_id,knowledge) VALUES(14,'狭义相对论的基本假设');
INSERT INTO knowledge (module_id,knowledge) VALUES(14,'质速关系、质能关系');
INSERT INTO knowledge (module_id,knowledge) VALUES(14,'相对论质能关系式');


--
-- table knowledginfo
--
DROP TABLE IF EXISTS `knowledge_info`;
CREATE TABLE knowledge_info(
`id` bigint(20) auto_increment ,
`year_id` bigint (20) NOT NULL,
`province_id` bigint (20) NOT NULL,
`module_id` bigint (20) NOT NULL,
`level_id` bigint (20) NOT NULL,
`knowledge_id` bigint (20) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`),
FOREIGN KEY(year_id) REFERENCES year(id),
FOREIGN KEY(province_id) REFERENCES province(id),
FOREIGN KEY(module_id) REFERENCES knowledge_module(id),
FOREIGN KEY(level_id) REFERENCES knowledge_level(id),
FOREIGN KEY(knowledge_id) REFERENCES knowledge(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- DROP INDEX IF EXISTS `IX_knowledgeinfo_yearid`;
CREATE INDEX IX_knowledgeinfo_yearid ON knowledge_info(year_id);
-- DROP INDEX IF EXISTS `IX_knowledgeinfo_provinceid`;
CREATE INDEX IX_knowledgeinfo_provinceid ON knowledge_info(province_id);
-- DROP INDEX IF EXISTS `IX_knowledgeinfo_moduleid`;
CREATE INDEX IX_knowledgeinfo_moduleid ON knowledge_info(module_id);
-- DROP INDEX IF EXISTS `IX_knowledgeinfo_levelid`;
CREATE INDEX IX_knowledgeinfo_levelid ON knowledge_info(level_id);
-- DROP INDEX IF EXISTS `IX_knowledgeinfo_knowledgeid`;
CREATE INDEX IX_knowledgeinfo_knowledgeid ON knowledge_info(knowledge_id);

--
-- Initiate table knowledge_info
--

INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,1,1,1);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,1,2,2);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,1,2,3);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,2,1,4);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,2,1,5);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,2,1,6);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,2,2,7);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,2,2,8);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,2,2,9);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,2,1,10);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,3,2,11);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,3,2,12);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,3,2,13);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,3,2,14);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,4,2,15);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,4,2,16);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,4,1,17);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,4,2,18);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,4,1,19);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,5,2,20);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,5,2,21);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,5,1,22);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,5,1,23);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,24);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,25);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,26);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,2,27);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,28);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,2,29);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,30);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,31);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,2,32);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,33);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,2,34);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,35);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,6,1,36);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,7,2,37);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,7,1,38);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,7,1,39);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,7,2,40);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,7,2,41);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,7,1,42);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,8,1,43);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,8,1,44);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,8,1,45);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,8,2,46);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,8,1,47);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,8,2,48);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,8,2,49);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,8,1,50);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,9,1,51);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,9,1,52);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,9,2,53);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,9,2,54);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,9,1,55);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,10,1,56);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,10,1,57);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,10,1,58);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,10,1,59);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,1,60);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,2,61);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,1,62);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,1,63);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,1,64);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,1,65);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,2,66);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,2,67);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,1,68);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,11,1,69);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,12,1,70);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,12,1,71);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,12,1,72);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,13,2,73);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,13,1,74);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,13,1,75);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,13,1,76);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,14,1,77);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,14,1,78);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (10,8,14,1,79);

INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,1,1,1);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,1,2,2);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,1,2,3);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,2,1,4);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,2,1,5);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,2,1,6);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,2,2,7);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,2,2,8);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,2,2,9);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,2,1,10);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,3,2,11);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,3,2,12);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,3,2,13);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,3,2,14);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,4,2,15);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,4,2,16);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,4,1,17);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,4,2,18);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,4,1,19);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,5,2,20);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,5,2,21);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,5,1,22);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,5,1,23);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,24);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,25);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,26);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,2,27);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,28);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,2,29);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,30);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,31);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,2,32);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,33);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,2,34);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,35);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,6,1,36);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,7,2,37);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,7,1,38);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,7,1,39);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,7,1,40);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,7,2,41);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,7,1,42);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,8,1,43);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,8,1,44);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,8,1,45);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,8,2,46);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,8,1,47);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,8,2,48);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,8,2,49);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,8,1,50);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,9,1,51);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,9,1,52);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,9,2,53);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,9,2,54);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,9,1,55);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,10,1,56);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,10,1,57);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,10,1,58);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,10,1,59);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,1,60);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,2,61);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,1,62);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,1,63);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,1,64);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,1,65);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,2,66);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,2,67);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,1,68);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,11,1,69);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,12,1,70);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,12,1,71);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,12,1,72);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,13,2,73);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,13,1,74);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,13,1,75);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,13,1,76);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,14,1,77);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,14,1,78);
INSERT INTO knowledge_info(year_id,province_id,module_id,level_id,knowledge_id) 
VALUES (9,8,14,1,79);

--
-- table question type 题目类型
--
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type` (
`id` bigint(20) auto_increment ,
`type` varchar(20) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- table question image
--
DROP TABLE IF EXISTS `question_image`;
CREATE TABLE `question_image` (
`id` bigint(20) auto_increment ,
`image_url` varchar(500) NOT NULL,
`image_description` varchar(200) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- init table question_type
--

INSERT INTO question_type(type) VALUES ('单选');
INSERT INTO question_type(type) VALUES ('多选');
INSERT INTO question_type(type) VALUES ('填空');
INSERT INTO question_type(type) VALUES ('问答');


--
-- Table question -- 大题的一，二，三问，待处理！
--
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
`question_id` bigint(20) auto_increment ,
`question` varchar(8000),
`question_type_id` bigint(20) NOT NULL,
`construct_type_id` bigint(20) NOT NULL DEFAULT 1,
`answer` text,
`analysis` text,
`active` int DEFAULT 1 ,
PRIMARY KEY(`question_id`),
FOREIGN KEY(question_type_id) REFERENCES question_type(id),
FOREIGN KEY(construct_type_id) REFERENCES construct_type(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- DROP INDEX IF EXISTS `IX_question_questiontypeid`;
CREATE INDEX IX_question_questiontypeid ON question(question_type_id);

-- DROP INDEX IF EXISTS `IX_question_constructtypeid`;
CREATE INDEX IX_question_constructtypeid ON question(construct_type_id);

--
-- init table question
--
-- INSERT INTO question(question,question_type_id,answer)
--			  VALUES('如图所示，一物块位于光滑水平桌面上，用一大小为F、方向如图所示的力去推它，使它以加速度a向右运动。若保持力的方向不变而增大力的大小，则（    )',1,'A');
-- INSERT INTO question(question,question_type_id,answer)
-- 			  VALUES('氢原子的能级图如图所示。欲使一处于基态的氢原子释放出一个电子而变成氢离子，该氢原子需要吸收的能量至少是（    ）',1,'A');
--
-- question_selection
--
DROP TABLE IF EXISTS `question_selection`;
CREATE TABLE `question_selection` (
`question_selection_id` bigint(20) auto_increment ,
`question_id` bigint NOT NULL,
`selection_index` varchar(2) NOT NULL,
`selection` varchar(2000) NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`question_selection_id`),
FOREIGN KEY(question_id) REFERENCES question(question_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- DROP INDEX IF EXISTS `IX_question_selection_question_id`;
CREATE INDEX IX_question_selection_question_id ON question_selection(question_id);

--
-- init question_selection
--
-- INSERT INTO question_selection (question_id,selection_index,selection) VALUES (1,'A','a变大');
-- INSERT INTO question_selection (question_id,selection_index,selection) VALUES (1,'B','a不变');
-- INSERT INTO question_selection (question_id,selection_index,selection) VALUES (1,'C','a变小');
-- INSERT INTO question_selection (question_id,selection_index,selection) VALUES (1,'D','因为物块的质量未知，故不能确定a变化的趋势');
-- INSERT INTO question_selection (question_id,selection_index,selection) VALUES (2,'A','13.60eV');
-- INSERT INTO question_selection (question_id,selection_index,selection) VALUES (2,'B','10.20eV');
-- INSERT INTO question_selection (question_id,selection_index,selection) VALUES (2,'C','0.54eV');
-- INSERT INTO question_selection (question_id,selection_index,selection) VALUES (2,'D','27.20eV');

--
-- table question_has_check_type
--
DROP TABLE IF EXISTS `question_has_check_type`;
CREATE TABLE `question_has_check_type` (
`id` bigint(20) auto_increment ,
`question_id` bigint NOT NULL,
`check_type_id` bigint NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`),
FOREIGN KEY(question_id) REFERENCES question(question_id),
FOREIGN KEY(check_type_id) REFERENCES check_type(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- DROP INDEX IF EXISTS `IX_question_has_check_type_question_id`;
CREATE INDEX IX_question_has_check_type_question_id ON question_has_check_type(question_id);

-- DROP INDEX IF EXISTS `IX_question_has_check_type_check_type_id`;
CREATE INDEX IX_question_has_check_type_check_type_id ON question_has_check_type(check_type_id);

--
-- init question_has_check_type
--

-- INSERT INTO question_has_check_type(question_id,check_type_id) VALUES (1,1);
-- INSERT INTO question_has_check_type(question_id,check_type_id) VALUES (2,1);

--
-- Table question has subquestion. QA only.问答题的子问题表
--

DROP TABLE IF EXISTS `subquestion`;
CREATE TABLE `subquestion`(
	`id` bigint(20) auto_increment ,
	`subquestion` varchar(2000) NOT NULL,
	`question_id` bigint NOT NULL,
	`score_id` bigint,
	`sqindex` int NOT NULL,
	`active` int DEFAULT 1 ,
	PRIMARY KEY(`id`),
	FOREIGN KEY(question_id) REFERENCES question(question_id),
	FOREIGN KEY(score_id) REFERENCES score(id)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX IX_subquestion_question_id on subquestion(question_id);
--
-- question_has_knowledge
--

DROP TABLE IF EXISTS `question_has_knowledge`;
CREATE TABLE `question_has_knowledge` (
`id` bigint(20) auto_increment ,
`question_id` bigint NOT NULL,
`knowledge_id` bigint NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`),
FOREIGN KEY(question_id) REFERENCES question(question_id),
FOREIGN KEY(knowledge_id) REFERENCES knowledge(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- DROP INDEX IF EXISTS `IX_question_has_knowledge_question_id`;
CREATE INDEX IX_question_has_knowledge_question_id ON question_has_knowledge(question_id);

-- DROP INDEX IF EXISTS `IX_question_has_knowledge_knowledge_id`;
CREATE INDEX IX_question_has_knowledge_knowledge_id ON question_has_knowledge(knowledge_id);

--
-- init question_has_knowledge
--
-- INSERT INTO question_has_knowledge(question_id,knowledge_id) VALUES (1,2);
-- INSERT INTO question_has_knowledge(question_id,knowledge_id) VALUES (2,3);

--
-- table question has image
--
DROP TABLE IF EXISTS `question_has_image`;
CREATE TABLE `question_has_image`(
`id` bigint(20) auto_increment ,
`question_id` bigint NOT NULL,
`question_image_id` bigint NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`),
FOREIGN KEY(question_id) REFERENCES question(question_id),
FOREIGN KEY(question_image_id) REFERENCES question_image(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- table question metadata
--

DROP TABLE IF EXISTS `question_metadata`;
CREATE TABLE `question_metadata`(
`id` bigint(20) auto_increment,
`course_id` bigint NOT NULL,
`question_id` bigint(20) NOT NULL,
`province_id` bigint(20) NOT NULL,
`year_id`  bigint(20) NOT NULL,
`score_id` bigint(20) NOT NULL,
`difficulty_type_id` bigint NOT NULL,
`question_number` int NOT NULL,
`question_index` int NOT NULL,
`active` int DEFAULT 1 ,
PRIMARY KEY(`id`),
FOREIGN KEY(province_id) REFERENCES province(id),
FOREIGN KEY(year_id) REFERENCES year(id),
FOREIGN KEY(score_id) REFERENCES score(id),
FOREIGN KEY(question_id) REFERENCES question(question_id),
FOREIGN KEY(difficulty_type_id) REFERENCES difficulty_type(id),
FOREIGN KEY(course_id) REFERENCES course(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX IX_question_metadata_questionid ON question_metadata(question_id);
CREATE INDEX IX_question_metadata_provinceid ON question_metadata(province_id);
CREATE INDEX IX_question_metadata_yearid ON question_metadata(year_id);
CREATE INDEX IX_question_metadata_scoreid ON question_metadata(score_id);
CREATE INDEX IX_question_metadata_difficultytypeid ON question_metadata(difficulty_type_id);
CREATE INDEX IX_question_metadata_courseid ON question_metadata(course_id);

-- ------------------------------------------------------------View--------------------------------------------------------------
--
-- View for display question basic information.
--

DROP VIEW IF EXISTS `V_question_basic_info`;
CREATE VIEW V_question_basic_info
AS
select question.question_id,course.id as course_id,course.course,province.id as province_id,province.province,year.id as year_id,year.year,question.question from question 
join question_metadata on question.question_id = question_metadata.question_id
join province on province.id = question_metadata.province_id
join year on year.id = question_metadata.year_id
join course on course.id = question_metadata.course_id;

--
-- View for question full information.
--

DROP VIEW IF EXISTS `V_question_full_info`;
CREATE VIEW V_question_full_info
AS
SELECT 
question.question_id,
exam_type.id AS exam_type_id,
exam_type.type AS exam_type,
province.id AS province_id,
province.province,
year.id AS year_id,
year.year,
score.id AS score_id,
score.score,
course.id AS course_id,
course.course,
question_metadata.question_index,
question_metadata.question_number,
question_type.id AS question_type_id,
question_type.type AS question_type,
difficulty_type.id AS difficulty_type_id,
difficulty_type.type AS question_difficulty_type,
question.question,
book.book,
knowledge_module.modulename,
knowledge.knowledge
FROM question
JOIN question_metadata ON question_metadata.question_id = question.question_id
JOIN difficulty_type ON difficulty_type.id = question_metadata.difficulty_type_id
JOIN course ON course.id = question_metadata.course_id
JOIN province ON province.id = question_metadata.province_id
JOIN year ON year.id = question_metadata.year_id
JOIN score ON score.id = question_metadata.score_id
JOIN question_type ON question.question_type_id = question_type.id
LEFT JOIN question_has_knowledge ON question_has_knowledge.question_id = question.question_id
LEFT JOIN knowledge ON knowledge.id = question_has_knowledge.knowledge_id
LEFT JOIN knowledge_module ON knowledge.module_id = knowledge_module.id
LEFT JOIN book ON book.id = knowledge_module.book_id
LEFT JOIN exam_type ON exam_type.id = course.exam_type_id;

--
-- View for display question knowledge information.
--

DROP VIEW IF EXISTS `V_question_knowledge_info`;
CREATE VIEW V_question_knowledge_info
AS
SELECT exam_type.type,question.question,course.course,book.book,knowledge.knowledge FROM question_has_knowledge
JOIN question ON question.question_id = question_has_knowledge.question_id
JOIN knowledge ON knowledge.id = question_has_knowledge.knowledge_id
JOIN knowledge_module ON knowledge.module_id = knowledge_module.id
JOIN book ON book.id = knowledge_module.book_id
JOIN course ON book.course_id = course.id
JOIN exam_type ON exam_type.id = course.exam_type_id;


-- -----------------------------------------------------------SP------------------------------------------------------------------

-- call uspAddQuestion('test question content',8,4,8,10,1,1,1,17);


DROP PROCEDURE IF EXISTS `uspAddQuestion`;
DELIMITER //
CREATE PROCEDURE uspAddQuestion(
	question varchar(8000),
	provinceId bigint,
	questionTypeId bigint,
	courseId bigint,
	yearId bigint,
	difficultTypeId bigint,
	questionNumber int,
	questionIndex int,
	scoreValue int,
	optionA varchar(2000),
	optionB varchar(2000),
	optionC varchar(2000),
	optionD varchar(2000),
	subquestion1 varchar(200),
	subquestion2 varchar(200),
	subquestion3 varchar(200),
	subquestion4 varchar(200)
	)
BEGIN
	DECLARE scoreId INT;
	DECLARE questionId INT;
	
	SELECT id into scoreId from score where score = scoreValue;
	
	IF scoreId IS NULL THEN
		INSERT INTO score(score) VALUES (scoreValue);
		SELECT id into scoreId from score where score = scoreValue;		
	END IF;
	-- scoreId ready.
	
	SELECT auto_increment INTO questionId
	FROM information_schema.tables 
	WHERE table_schema = 'ncee' AND table_name='question' LIMIT 1;
	-- get the next question ID of the question table.
	
	START TRANSACTION; -- Begin transcation
	
	INSERT INTO question(question,question_type_id) VALUES (question,questionTypeId);
	-- Insert new item of question.
	
	INSERT INTO question_metadata (course_id,question_id,province_id,year_id,score_id,difficulty_type_id,question_number,question_index)
		VALUES (courseId,questionId,provinceId,yearId,scoreId,difficultTypeId,questionNumber,questionIndex);	
	-- Insert new item of metadata information of question.
	
	IF questionTypeId = 1 OR questionTypeId = 2 THEN
		IF optionA != '' THEN
			INSERT INTO question_selection (question_id,selection_index,selection)
				VALUES (questionId,'A',optionA);
		END IF;
		IF optionB != '' THEN
			INSERT INTO question_selection (question_id,selection_index,selection)
				VALUES (questionId,'B',optionB);
		END IF;
		IF optionC != '' THEN
			INSERT INTO question_selection (question_id,selection_index,selection)
				VALUES(questionID,'C',optionC);
		END IF;
		IF optionD != '' THEN
			INSERT INTO question_selection (question_id,selection_index,selection)
				VALUES(questionID,'D',optionD);
		END iF;
	END IF;
	IF questionTypeId = 4 THEN
		IF subquestion1 != '' THEN
			INSERT INTO subquestion(subquestion,question_id,sqindex) VALUES (subquestion1,questionId,1);
		END IF;
		IF subquestion2 != '' THEN
			INSERT INTO subquestion(subquestion,question_id,sqindex) VALUES (subquestion2,questionId,2);
		END IF;
		IF subquestion3 != '' THEN
			INSERT INTO subquestion(subquestion,question_id,sqindex) VALUES (subquestion3,questionId,3);
		END IF;
		IF subquestion4 != '' THEN
			INSERT INTO subquestion(subquestion,question_id,sqindex) VALUES (subquestion4,questionId,4);
		END IF;
	END IF;
	COMMIT; -- End transaction
END//
DELIMITER ;
--
-- Procedure for update question related information
--

DROP PROCEDURE IF EXISTS `uspUpdateQuestion`;
DELIMITER //
CREATE PROCEDURE uspUpdateQuestion(
	questionId bigint,
	question varchar(8000),
	provinceId bigint,
	questionTypeId bigint,
	courseId bigint,
	yearId bigint,
	difficultTypeId bigint,
	questionNumber int,
	questionIndex int,
	scoreValue int,
	optionA varchar(2000),
	optionB varchar(2000),
	optionC varchar(2000),
	optionD varchar(2000),
	subquestion1 varchar(200),
	subquestion2 varchar(200),
	subquestion3 varchar(200),
	subquestion4 varchar(200)
	)
BEGIN
-- Update the related information whatever if it's updated.
	DECLARE scoreId INT;
	DECLARE originQuestionTypeId INT;
	
START TRANSACTION;
	
	SELECT id into scoreId from score where score = scoreValue;
	
	IF (scoreId IS NULL) THEN
		INSERT INTO score(score) VALUES (scoreValue);
		SELECT id into scoreId from score where score = scoreValue;
	END IF;
	-- scoreId ready.
	SELECT question_type_id INTO originQuestionTypeId FROM question WHERE question_id = questionId;
	-- origin question type id ready.
	
	IF (originQuestionTypeId = 1 OR originQuestionTypeId = 2) AND (questionTypeId = 3 OR questionTypeId = 4) THEN
		DELETE FROM question_selection WHERE question_id = questionId;
	END IF;
	-- 选择题转其他题型，则删除原有选项
	
	IF originQuestionTypeId = 4 AND questionId != 4 THEN
		DELETE FROM subquestion WHERE question_id = questionId;
	END IF;
	-- 问答题转其他题型，则删除原有子问题
	
	
	
UPDATE question SET question.question = question,question_type_id = questionTypeId WHERE question.question_id = questionId;

UPDATE question_metadata SET course_id = courseId,
	province_id = provinceId,
	year_id = yearId,
	score_id = scoreId,
	difficulty_type_id = difficultTypeId,
	question_number = questionNumber,
	question_index = questionIndex
WHERE question_id = questionId;
   -- question metadata updated done.
   
	IF (questionTypeId = 1 OR questionTypeId = 2) THEN
		IF (optionA <> '') THEN
			IF EXISTS(SELECT * FROM question_selection WHERE question_id = questionId AND selection_index = 'A') THEN
				UPDATE question_selection SET selection = optionA WHERE question_id = questionId AND selection_index = 'A';
			ELSE
				INSERT INTO question_selection (question_id,question_index,selection) VALUES (questionId,'A',optionA);
			END IF;
		END IF;
		
		IF (optionB <> '') THEN
			IF EXISTS(SELECT * FROM question_selection WHERE question_id = questionId AND selection_index = 'B') THEN
				UPDATE question_selection SET selection = optionB WHERE question_id = questionId AND selection_index = 'B';
			ELSE 
				INSERT INTO question_selection (question_id,question_index,selection) VALUES (questionId,'B',optionB);
			END IF;
		END IF;
		
		IF (optionC != '') THEN
			IF EXISTS(SELECT * FROM question_selection WHERE question_id = questionId AND selection_index = 'C') THEN
				UPDATE question_selection SET selection = optionC WHERE question_id = questionId AND selection_index = 'C';
			ELSE 
				INSERT INTO question_selection (question_id,question_index,selection) VALUES (questionId,'C',optionC);
			END IF;
		END IF;
		
		IF (optionD != '') THEN
			IF EXISTS(SELECT * FROM question_selection WHERE question_id = questionId AND selection_index = 'D') THEN
				UPDATE question_selection SET selection = optionD WHERE question_id = questionId AND selection_index = 'D';
			ELSE 
				INSERT INTO question_selection (question_id,question_index,selection) VALUES (questionId,'D',optionD);
			END IF;
		END IF;
	END IF;
	
	IF(questionTypeId = 4) THEN
		IF(subquestion1 <> '') THEN
			IF EXISTS(SELECT * from subquestion WHERE question_id = questionId AND sqindex = 1) THEN
				UPDATE subquestion SET subquestion = subquestion1 WHERE question_id = questionId AND sqindex = 1;
			ELSE 
				INSERT INTO subquestion(subquestion,question_id,sqindex) VALUES (subquestion1,questionId,1);
			END IF;
		END IF;
		IF(subquestion2 != '') THEN
			IF EXISTS(SELECT * from subquestion WHERE question_id = questionId AND sqindex = 2) THEN
				UPDATE subquestion SET subquestion = subquestion2 WHERE question_id = questionId AND sqindex = 2;
			ELSE 
				INSERT INTO subquestion(subquestion,question_id,sqindex) VALUES (subquestion2,questionId,1);
			END IF;
		END IF;
		IF(subquestion3 != '') THEN
			IF EXISTS(SELECT * from subquestion WHERE question_id = questionId AND sqindex = 3) THEN
				UPDATE subquestion SET subquestion = subquestion3 WHERE question_id = questionId AND sqindex = 3;
			ELSE 
				INSERT INTO subquestion(subquestion,question_id,sqindex) VALUES (subquestion1,questionId,3);
			END IF;
		END IF;
		IF(subquestion4 != '') THEN
			IF EXISTS(SELECT * from subquestion WHERE question_id = questionId AND sqindex = 4) THEN
				UPDATE subquestion SET subquestion = subquestion4 WHERE question_id = questionId AND sqindex = 4;
			ELSE 
				INSERT INTO subquestion(subquestion,question_id,sqindex) VALUES (subquestion1,questionId,4);
			END IF;
		END IF;
	END IF;
COMMIT;
END//
DELIMITER ;

--
-- Procedure for removing a question
--

DROP PROCEDURE IF EXISTS `uspDeleteQuestion`;
DELIMITER //
CREATE PROCEDURE uspDeleteQuestion(questionId bigint)
BEGIN
	
	START TRANSACTION;
	
	DELETE FROM question_selection WHERE question_id = questionId;
	DELETE FROM subquestion WHERE question_id = questionId;
	DELETE FROM question_has_check_type WHERE question_id = questionId;
	DELETE FROM question_has_knowledge WHERE question_id = questionId;
	DELETE FROM question_metadata WHERE question_id = questionId;
	DELETE FROM question WHERE question_id = questionId;
	
	COMMIT;
	
END //
DELIMITER ;

-- ------------------------------------------------------------------------ END

DROP PROCEDURE IF EXISTS `uspTest`;

DELIMITER //
CREATE PROCEDURE uspTest()
BEGIN 
select year.year,province.province,course.course,knowledge_module.modulename,knowledge_level.level,knowledge.knowledge from knowledge_info 
join year on year.id = knowledge_info.year_id
join province on province.id = knowledge_info.province_id
join knowledge_module on knowledge_module.id = knowledge_info.module_id
join knowledge_level on knowledge_level.id = knowledge_info.level_id
join knowledge on knowledge.id = knowledge_info.knowledge_id
join book on knowledge_module.book_id = book.id
join course on book.course_id = course.id
where knowledge_info.year_id = 10
order by knowledge_info.module_id asc;
END//
DELIMITER ;
