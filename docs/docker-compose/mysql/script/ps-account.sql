DROP DATABASE IF EXISTS `ps_account`;
CREATE DATABASE `ps_account` DEFAULT CHARACTER SET utf8;

USE ps_account;
--
-- alibaba official demo
--
DROP TABLE IF EXISTS `account_tbl`;
CREATE TABLE `account_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- ps
--
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`(
	`id` bigint auto_increment primary key,
    `customer_id` bigint NOT NULL,
    `deposit` int NOT NULL DEFAULT 0,
    `create_time` timestamp NOT NULL DEFAULT now(),
    `update_time` timestamp NOT NULL DEFAULT now()
) ENGINE = InnoDB DEFAULT CHARSET= utf8;