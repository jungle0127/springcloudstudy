DROP DATABASE IF EXISTS `ps_storage`;
CREATE DATABASE `ps_storage` DEFAULT CHARACTER SET utf8;

USE ps_storage;
--
-- alibaba official demo
--
DROP TABLE IF EXISTS `storage_tbl`;
CREATE TABLE `storage_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_code` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`commodity_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO storage_tbl(commodity_code,count) VALUES('cc1',1000);
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_unionkey` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;

--
-- demo
--
DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage`(
	`id` bigint auto_increment primary key,
    `product_name` varchar(255) NOT NULL,
    `inventory` int NOT NULL,
    `create_time` timestamp default now(),
    `update_time` timestamp default now()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_storage( `product_name`,`inventory`) VALUES ('pseudo product',1000);